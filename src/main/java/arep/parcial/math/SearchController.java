package arep.parcial.math;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class SearchController {

    @GetMapping("/linearsearch")
    public ResponseEntity<?> linearSearch(@RequestParam("list") String listStr, @RequestParam("value") String valueStr){
        String[] list = listStr.split(",");
        int index = -1;
        for (int i = 0; i < list.length; i++){
            if (list[i].equals(valueStr)){
                index = i;
            }
        }

        Map<String, String> response = new LinkedHashMap<>();
        response.put("operation", "linearSearch");
        response.put("inputlist", listStr);
        response.put("value", valueStr);
        response.put("output", String.valueOf(index));
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/binarysearch")
    public ResponseEntity<?> binarySearch(@RequestParam("list") String listStr, @RequestParam("value") String valueStr){
        String[] list = listStr.split(",");
        int[] listInt = new int[list.length];
        for (int i = 0; i < list.length; i++){
            listInt[i] = Integer.parseInt(list[i]);
        }
        Arrays.sort(listInt);
        System.out.println(Arrays.toString(listInt));

        int index = binarySearchRecursive(listInt, Integer.parseInt(valueStr), 0, listInt.length-1);
        Map<String, String> response = new LinkedHashMap<>();
        response.put("operation","binarySearch");
        response.put("inputlist", listStr);
        response.put("value", valueStr);
        response.put("output", String.valueOf(index));
        return ResponseEntity.ok().body(response);
    }

    private int binarySearchRecursive(int[] list, int value, int left, int right) {
        if (left > right) return -1;

        int mid = (left + right) / 2;

        if (list[mid] == value) return mid;
        else if (list[mid] > value) return binarySearchRecursive(list, value, left, mid-1);
        else return binarySearchRecursive(list, value,mid+1,right);

    }


}
