package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import enums.Privacy;
import exception.BoardNotFound;
import exception.UserDoesNotExist;
import model.Board;
import model.BoardList;
import model.User;
import utils.JsonWrapper;

import java.util.HashMap;
import java.util.Map;

public class BoardService {
    private Map<String, Board> boards;

    public BoardService() {
        this.boards = new HashMap<>();
    }

    // Board
    public Board createBoard(String name) {
        Board board = new Board(name);
        boards.put(board.getId(), board);
        System.out.println("Created board: " + board.getId());
        return board;
    }

    public void deleteBoard(String boardId) {
        if (!isBoardExist(boardId)) {
            throw new BoardNotFound("Board does not exist");
        }
        boards.remove(boardId);
        System.out.println("Deleted Board: " + boardId);
    }

    public void showBoard() throws JsonProcessingException {
        if(boards.size() == 0) {
            System.out.println("No boards");
            return;
        }
        for(String boardId: boards.keySet()) {
            String jsonString = JsonWrapper.jsonString(getBoardById(boardId));
            System.out.println(jsonString);
        }
    }

    public void showBoardWithId(String boardId) throws JsonProcessingException {
        Board board = getBoardById(boardId);
        String jsonString = JsonWrapper.jsonString(board);
        System.out.println(jsonString);
    }

    public void updateBoardName(String boardId, String boardName) {
        if (!isBoardExist(boardId)) {
            throw new BoardNotFound("Board does not exist");
        }
        boards.get(boardId).updateName(boardName);
        System.out.println("Updated Board name as " + boards.get(boardId).getName());
    }

    public void updateBoardPrivacy(String boardId, String privacy) {
        if (!isBoardExist(boardId)) {
            throw new BoardNotFound("Board does not exist");
        }
        boards.get(boardId).updatePrivacy(Privacy.valueOf(privacy));
        System.out.println("Updated Board privacy as " + boards.get(boardId).getPrivacy());
    }

    public void addMemberInBoard(String boardId, User member) {
        if (!isBoardExist(boardId)) {
            throw new BoardNotFound("Board does not exist");
        }
        if(boards.get(boardId).isMemberExist(member)) {
            System.out.println("User " + member.getUserId() + " already exist");
            return;
        }
        boards.get(boardId).addMember(member);
        System.out.println("Added member " + member.getUserId() + " in Board: " + boardId);
    }

    public void removeMemberFromBoard(String boardId, User member) {
        if (!isBoardExist(boardId)) {
            throw new BoardNotFound("Board does not exist");
        }
        if(!boards.get(boardId).isMemberExist(member)) {
            throw new UserDoesNotExist("User does not exist");
        }
        boards.get(boardId).removeMember(member);
        System.out.println("Removed member " + member.getUserId() + " from Board: " + boardId);
    }

    public void addListToBoard(String boardId, BoardList list) {
        if (!isBoardExist(boardId)) {
            throw new BoardNotFound("Board does not exist");
        }
        boards.get(boardId).addList(list);
        System.out.println("Added list to board");
    }

    public void removeListFromBoard(String boardId, BoardList list) {
        if (!isBoardExist(boardId)) {
            throw new BoardNotFound("Board does not exist");
        }
        boards.get(boardId).removeList(list);
        System.out.println("Removed list from board");
    }

    // helper
    public Board getBoardById(String boardId) {
        if (!isBoardExist(boardId)) {
            throw new BoardNotFound("Board does not exist");
        }
        return boards.get(boardId);
    }

    public Board getBoardToWhichListBelongs(BoardList list) {
        for(String boardId: boards.keySet()) {
            if(getBoardById(boardId).isBoardListExist(list)) {
                return getBoardById(boardId);
            }
        }
        return null;
    }

    public boolean isBoardExist(String boardId) {
        return boards.containsKey(boardId);
    }

}
