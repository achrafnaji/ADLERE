package com.bnp.trainscompany.infra.io;

import com.bnp.trainscompany.infra.io.read.Taps;
import com.bnp.trainscompany.infra.io.write.Summaries;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

// Read from JSON - Writ To JSON 
public class Utils {
	
    static ObjectMapper mapper = new ObjectMapper();
    static Taps taps = new Taps();
    public static Taps fromJsonFile(String filePath){
        try {
          taps = mapper.readValue(new File(filePath), Taps.class);  
        } catch (IOException e) {
            e.printStackTrace();
        }
       return taps;
    }

    public static void toJsonFile(String filePath, Summaries summaries){
        try {
            mapper.writeValue(new File(filePath), summaries);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
