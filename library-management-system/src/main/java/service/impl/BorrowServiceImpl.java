package service.impl;

import model.BookBorrowDetails;
import service.BorrowServiceReader;
import service.BorrowServiceWriter;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class BorrowServiceImpl implements BorrowServiceReader, BorrowServiceWriter {
    private static Map<Integer, Set<BookBorrowDetails>> userBorrowedBookCntMap; // userId -> Cnt of books borrowed
    private static Map<Integer, BookBorrowDetails> bookBorrowDetailsMap; // bookCopyId -> borrowDetails
    private static final Integer MAX_LIMIT_TO_BORROW = 5;
    private final Comparator<BookBorrowDetails> BORROW_DETAILS_COMPARATOR = new Comparator<BookBorrowDetails>() {
        @Override
        public int compare(BookBorrowDetails o1, BookBorrowDetails o2) {
            return o1.getBookCopyId().compareTo(o2.getBookCopyId());
        }
    };

    public BorrowServiceImpl() {
        userBorrowedBookCntMap = new HashMap<>();
        bookBorrowDetailsMap = new TreeMap<>();  // used TreeMap since we have to print it in ascending order by key
    }

    public BookBorrowDetails getBookBorrowDetailsForBookId(Integer bookCopyId){
        return bookBorrowDetailsMap.get(bookCopyId);
    }

    public Boolean checkIfUserIsAllowedToBorrowBook(Integer userId){
        System.out.println("User has borrowed " + userBorrowedBookCntMap.getOrDefault(userId, Collections.emptySet()).size() + " books so far");
        return userBorrowedBookCntMap.getOrDefault(userId, Collections.emptySet()).size() < MAX_LIMIT_TO_BORROW;
    }

    public void addBookCopyToUserBorrowDetails(Integer userId, Integer bookCopyId, Date dueDate){
        BookBorrowDetails bookBorrowDetails = new BookBorrowDetails(bookCopyId, userId, dueDate);
        if(!userBorrowedBookCntMap.containsKey(userId)){
            userBorrowedBookCntMap.put(userId, new TreeSet<>(BORROW_DETAILS_COMPARATOR));
        }
        userBorrowedBookCntMap.get(userId).add(bookBorrowDetails);
        bookBorrowDetailsMap.put(bookCopyId, bookBorrowDetails);
    }


    public void removeBookCopyFromUserBorrowDetails(Integer bookCopyId){
        BookBorrowDetails bookBorrowDetails = bookBorrowDetailsMap.get(bookCopyId);
        Integer userId = bookBorrowDetails.getUserId();
        if(!userBorrowedBookCntMap.containsKey(userId)){
            userBorrowedBookCntMap.put(userId, new TreeSet<>(BORROW_DETAILS_COMPARATOR));
        }
        userBorrowedBookCntMap.get(userId).remove(bookBorrowDetails);
        bookBorrowDetailsMap.remove(bookCopyId);
    }

    public void printBorrowDetails(Integer userId){
        for (BookBorrowDetails details:
                userBorrowedBookCntMap.getOrDefault(userId, Collections.emptySet())) {
            System.out.println("Book Copy: " + details.getBookCopyId() + " " + details.getDueDate());
        }
    }


}
