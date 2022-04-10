## Trello
Project Management Application

### Problem Link
https://workat.tech/machine-coding/practice/trello-problem-t0nwwqt61buz

### Application Terminology
- App contains multiple boards to signify different projects.
- Each board contains different lists to signify subproject
- Each list contain different cards signifying smaller tasks
- Each card can be assigned to a user or may remain unassigned

### Requirements
- Create, modify attributes, and add/remove people from the members list in Board.
- Delete Board and also delete all BoardList inside it. *(Do we need to delete the instance?)*
- Create and modify attributes in BoardList.
- Delete BoardList and also delete all cards inside it.
- Create/delete Card, assign/unassign member, modify attributes.
- Move cards across the BoardList in the same board.
- Show all Boards, single Board, single BoardList, single Card.
- Default privacy should be public.
- Cards should be unassigned by default.
- Ids should be auto-generated for Board/BoardList/Card
- URLs should get created based on the id.

### Optional Requirements
- [X] Ability to clone a list with all the cards in it. All of these should have a different id.
- [X] Ability to delete all the cards in a list without deleting the list
- [X] Option to add tags to a card and ability to get cards based on assigned tags.
- [X] Ability to find all the cards assigned to a particular user

### Commands
- BOARD CREATE
- BOARD <name/privacy>
- BOARD <ADD_MEMBER/REMOVE_MEMBER>
- BOARD DELETE
- LIST CREATE
- LIST
- LIST DELETE
- CARD CREATE
- CARD <name/description>
- CARD ASSIGN
- CARD UNASSIGN
- CARD MOVE
- CARD DELETE
- SHOW
- SHOW BOARD
- SHOW LIST
- SHOW CARD

### Entities
- User:-
  - userId, email, name
  - updateEmail(), updateName()
- Board:-
  - id, name, privacy (PUBLIC/PRIVATE), url, List<BoardList> lists, List<User> members
  - updateName(), updatePrivacy(), addList(), removeList(), showList(), addMember(), removeMember()
- BoardList:-
  - id, name, List<Card> cards, 
  - updateName, addCard(), removeCard(), showCard(), clearBoardList(), cloneList()
- Card:-
  - id, name, description, assigned user (Default - unassigned), tag
  - updateName(), updateDescription(), assignUser(), unassignUser(), addTag(), removeTag(), updateTag()
- 

### Enum
- Privacy:-
  - PUBLIC
  - PRIVATE

### Service
- TrelloService:-
  - Map<Integer (Board Id), Board> boards
  - createBoard(), deleteBoard(), showBoard(), moveCard()