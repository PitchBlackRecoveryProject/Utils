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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author manjotsidhu
 */
public class Release {

    public static JSONObject readFile(String s1, String s2) throws FileNotFoundException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        Reader reader = new FileReader("pb.releases");

        Object jsonObj = parser.parse(reader);

        JSONObject jsonObject = (JSONObject) jsonObj;

        writeToFile(jsonObject, s1, s2);

        reader.close();
        return jsonObject;
}

public static void writeToFile(JSONObject obj, String s1, String s2) {
        //JSONObject obj = new JSONObject();

        obj.put(s1, s2);

        try {
            FileWriter file = new FileWriter("pb.releases");
            file.write(obj.toJSONString());
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print(obj.toJSONString());
    }

    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException {
        readFile(args[0], args[1]);
    }
}
