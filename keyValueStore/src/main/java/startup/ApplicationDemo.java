package startup;

import exception.DataTypeException;
import model.CommandType;
import model.Value;
import service.DataService;
import service.DataServiceImpl;

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
