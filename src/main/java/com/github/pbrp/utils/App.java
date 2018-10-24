/*
 * Copyright 2018 Manjot Sidhu <manjot.techie@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pbrp.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author manjotsidhu
 */
public class App {
    
    public static int checkUpdate(String[] input) throws MalformedURLException, FileNotFoundException, IOException, ParseException {
        URL url = new URL("https://raw.githubusercontent.com/PitchBlack-Recovery/vendor_pb/pb/pb.releases");

        JSONParser parser = new JSONParser();
        Reader reader = new BufferedReader(new InputStreamReader(url.openStream()));

        Object jsonObj = parser.parse(reader);
        JSONObject release = (JSONObject) jsonObj;
                
        if(release.containsKey(input[0])) {
            if(release.get(input[0]).equals(input[1])) {
                // Official
                return 1;
            } else {
                // Update available
                return 0;
            }
            
        } else {
            // UnOfficial
            return -1;
        }
    }
    
    public static String[] parseInfo(File s) throws IOException {
        String[] result = new String[2];
        
        String info = FileUtils.readFileToString(s);
        
        Pattern ptn = Pattern.compile("ro.omni.version=[^\\n\\t-]+-(\\d+)-([^\\n\\t-]+)");
        Matcher matchPtn = ptn.matcher(info);
        
        if(matchPtn.find()) {
            result[1] = matchPtn.group(1);
            result[0] = matchPtn.group(2);
        }
        
        return result;
    }
    
    public static void main(String[] args) throws MalformedURLException, IOException, FileNotFoundException, ParseException {
        //System.out.println(checkUpdate());
        String[] r = parseInfo(new File("test"));
        System.out.println(Arrays.toString(r));
        System.out.println(checkUpdate(r));
    }
}
