package startup;

import exception.DataTypeException;
import model.CommandType;
import model.Value;
import service.DataService;
import service.DataServiceImpl;
import service.ThreadSafeDataServiceImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ApplicationDemo {
    public static void main(String[] args) throws Exception {

//        Uncomment to test
//        testPutMethod();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        DataService dataService = new DataServiceImpl();
        String input = br.readLine();
        while (!input.equals("exit")) {
            String[] inputs = input.split(" ");
            CommandType command = CommandType.getType(inputs[0]);
            System.out.println("Command type " + command);
            switch (command) {
                case GET:
                    String key = inputs[1];
                    Value value = dataService.get(key);
                    if (value == null) {
                        System.out.println("No entry found for " + key);
                        break;
                    }
                    System.out.println(value.toString());
                    break;
                case DELETE:
                    dataService.deleteKey(inputs[1]);
                    break;
                case PUT:
                    try {
                        dataService.put(inputs[1], buildAttributeList(inputs));
                    }catch (DataTypeException dataTypeException){
                        System.out.println(dataTypeException.getMessage());
                    }
                    break;
                case KEYS:
                    Set<String> keySet = dataService.keys();

                    // intial approach
//                    for (String key:
//                         keySet) {
//                        System.out.println(key + ",");
//                    }

                    // better way
                    System.out.println(keySet.stream().collect(Collectors.joining(",")));
                    break;
                case SEARCH:
                    List<String> keys = dataService.search(inputs[1], inputs[2]);
                    System.out.println(keys.stream().collect(Collectors.joining(",")));
                    break;
            }
            input = br.readLine();
        }
    }

    /**
     * Test Case 1: 10 threads inserts data into the map
     * When we read the value it should return the value set by last thread;
     */
    public static void testPutMethod() throws InterruptedException {
        DataService defaultStore = new DataServiceImpl();
        DataService concurrentStore = new ThreadSafeDataServiceImpl();
        int numThreads = 10;
        for (int i = 0; i < numThreads; i++) {
            int finalI = i;
            Thread t1 = new Thread(() -> {
                List<Map.Entry<String, String>> value = List.of(new AbstractMap.SimpleEntry("attri", String.valueOf(finalI)));
                try {
                    // each thread inserts 100 keys
                    for (int j = 0; j < 100; j++) {
                        String key = "key"+ finalI + j;
                        defaultStore.put(key, value);
                        if(defaultStore.get(key) == null){
                            System.out.println(key + " not found in default store");
                        }
                        concurrentStore.put(key, value);
                        if(concurrentStore.get(key) == null){
                            System.out.println(key + " not inserted in concurrent store");
                        }
                    }
                } catch (Exception exception) {
                    System.out.println("Exception in put method for value " + finalI);
                    exception.printStackTrace();
                }
            });
            t1.start();
        }

        Thread.sleep(1000);
        System.out.println("Final size of default hash map ( 10 threads * 100 keys ) =" + defaultStore.keys().size());
        System.out.println("Final size of concurrent map ( 10 threads * 100 keys ) =" + concurrentStore.keys().size());
    }

    public static List<Map.Entry<String, String>> buildAttributeList(String[] inputs){
        List<Map.Entry<String, String>> list = new LinkedList<>();
        Map.Entry<String, String> entry = null;
        for (int i = 2; i < inputs.length; i++) {
            if(i % 2 == 0){
                entry = new AbstractMap.SimpleEntry<>(String.valueOf(inputs[i]), null);
            }else if(entry != null){
                entry.setValue(inputs[i]);
                list.add(entry);
            }
        }
        return list;
    }
}
