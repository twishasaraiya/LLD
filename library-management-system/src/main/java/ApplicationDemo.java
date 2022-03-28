import enums.CommandType;
import service.LibraryManagementService;
import service.impl.LibraryServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApplicationDemo {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-mm-yyyy");

    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine(), " ");
        LibraryManagementService libraryManagementService = new LibraryServiceImpl();

        while (stringTokenizer.hasMoreTokens()) {
            CommandType commandType = CommandType.getCommand(stringTokenizer.nextToken());
            Integer bookId;
            Integer bookCopyId;
            Integer userId;
            Date dueDate;
            switch (commandType) {
                case CREATE_LIBRARY:
                    Integer numOfRacks = Integer.valueOf(stringTokenizer.nextToken());
                    libraryManagementService.createLibrary(numOfRacks);
                    break;
                case ADD_BOOK:
                    bookId = Integer.valueOf(stringTokenizer.nextToken());
                    String bookTitle = stringTokenizer.nextToken();
                    List<String> authors = Arrays.asList(stringTokenizer.nextToken().split(","));
                    List<String> publishers = Arrays.asList(stringTokenizer.nextToken().split(","));
                    List<Integer> bookCopyIds = new ArrayList<>();
                    for (String input : Arrays.asList(stringTokenizer.nextToken().split(","))) {
                        bookCopyIds.add(extractIds("book_copy", input));
                    }
                    libraryManagementService.addBook(bookId, bookTitle, authors, publishers, bookCopyIds);
                    break;
                case REMOVE_BOOK_COPY:
                    libraryManagementService.removeBookCopy(extractIds("book_copy",stringTokenizer.nextToken()));
                    break;
                case BORROW_BOOK:
                    bookId = Integer.valueOf(stringTokenizer.nextToken());
                    userId = extractIds("user", stringTokenizer.nextToken());
                    dueDate = DATE_FORMAT.parse(stringTokenizer.nextToken());
                    libraryManagementService.borrowBook(bookId, userId, dueDate);
                    break;
                case BORROW_BOOK_COPY:
                    bookCopyId = Integer.valueOf(stringTokenizer.nextToken());
                    userId = extractIds("user", stringTokenizer.nextToken());
                    dueDate = DATE_FORMAT.parse(stringTokenizer.nextToken());
                    libraryManagementService.borrowBookCopy(bookCopyId, userId, dueDate);
                    break;
                case RETURN_BOOK_COPY:
                    bookCopyId = Integer.valueOf(stringTokenizer.nextToken());
                    libraryManagementService.returnBookCopy(bookCopyId);
                    break;
                case PRINT_BORROWED:
                    userId = Integer.valueOf(stringTokenizer.nextToken());
                    libraryManagementService.printBorrowed(userId);
                    break;
                case SEARCH:
                    String attribute = stringTokenizer.nextToken();
                    String value = stringTokenizer.nextToken();
                    libraryManagementService.search(attribute, value);
                    break;
                case EXIT:
                    System.exit(1);
            }
            stringTokenizer = new StringTokenizer(br.readLine(), " ");
        }
    }

    private static Integer extractIds(String extractStr, String data) {
        Pattern pattern = Pattern.compile(extractStr + "(?<id>\\d*)");
        Matcher matcher = pattern.matcher(data);
        while (matcher.find()) {
            return Integer.valueOf(matcher.group("id"));
        }
        return -1;
    }
}
