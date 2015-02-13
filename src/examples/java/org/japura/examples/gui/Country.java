package org.japura.examples;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Country {

  public static List<String> getCountries() {
    List<String> list = new ArrayList<String>();
    Class<?> cls = Country.class;
    URL url = cls.getResource("/countries.txt");
    BufferedReader reader = null;

    try {
      reader = new BufferedReader(new FileReader(url.getFile()));
      String text = null;

      while ((text = reader.readLine()) != null) {
        list.add(text);
      }
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    finally {
      try {
        if (reader != null) {
          reader.close();
        }
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
    return list;
  }

}
