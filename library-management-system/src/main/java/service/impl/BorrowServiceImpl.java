package service.impl;

import model.BookBorrowDetails;
import service.BorrowServiceReader;
import service.BorrowServiceWriter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class BorrowServiceImpl implements BorrowServiceReader, BorrowServiceWriter {
    private static Map<Integer, Integer> userBorrowedBookCntMap; // userId -> Cnt of books borrowed
    private static Map<Integer, BookBorrowDetails> bookBorrowDetailsMap; // bookCopyId -> borrowDetails
    private static final Integer MAX_LIMIT_TO_BORROW = 5;

    public BorrowServiceImpl() {
        userBorrowedBookCntMap = new HashMap<>();
        bookBorrowDetailsMap = new TreeMap<>();  // used TreeMap since we have to print it in ascending order by key
    }

    public BookBorrowDetails getBookBorrowDetailsForBookId(Integer bookCopyId){
        return bookBorrowDetailsMap.get(bookCopyId);
    }

    public Boolean checkIfUserIsAllowedToBorrowBook(Integer userId){
        return userBorrowedBookCntMap.getOrDefault(userId, 0) < MAX_LIMIT_TO_BORROW;
    }

    public void addBookCopyToUserBorrowDetails(Integer userId, Integer bookCopyId, Date dueDate){
        userBorrowedBookCntMap.put(userId, userBorrowedBookCntMap.getOrDefault(userId, 0) + 1);
        BookBorrowDetails bookBorrowDetails = new BookBorrowDetails(bookCopyId, userId, dueDate);
        bookBorrowDetailsMap.put(bookCopyId, bookBorrowDetails);
    }


    public void removeBookCopyFromUserBorrowDetails(Integer bookCopyId){
        BookBorrowDetails bookBorrowDetails = bookBorrowDetailsMap.get(bookCopyId);
        Integer userId = bookBorrowDetails.getUserId();
        userBorrowedBookCntMap.put(userId, userBorrowedBookCntMap.get(userId) - 1);
        bookBorrowDetailsMap.remove(bookCopyId);
    }

    public void printBorrowDetails(){
        for (Integer bookCopyId:
                bookBorrowDetailsMap.keySet()) {
            System.out.println("Book Copy: " + bookCopyId + " " + bookBorrowDetailsMap.get(bookCopyId).getDueDate());
        }
    }


}
