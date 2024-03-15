package rest.spellchecking02.service.service;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import rest.spellchecking02.authentication.ContentCheck;
import rest.spellchecking02.service.serviceInterface.JsoupServiceInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Service
public class JsoupService implements JsoupServiceInterface {
    private final Set<String> dictionary;

    private JsoupService() throws IOException {
        dictionary = loadDictionary();
    }

    public ContentCheck getData(String link) throws IOException {
        Document doc = Jsoup.connect(link).get();
        ContentCheck result = new ContentCheck();
        result.title = doc.title();
        Elements data = doc.body().children();
        result.body = checkData(data);
        return result;
    }

    private String checkData(Elements elements) {
        if (elements == null) {
            return null;
        }

        String data = mergeText(elements);
        List<String> wrongWords = findWrongWords(data);
        StringBuilder html = new StringBuilder();
        if (wrongWords == null) {
            return "";
        }

        try {
            String head = "#:~:" + wrongWords.get(0);
            html.append(head);
            for (int i = 1; i < wrongWords.size(); i++) {
                html.append("&text=").append(wrongWords.get(i));
            }
        } catch (Exception exception) {
            return "";
        }
        return html.toString();
    }

    private List<String> findWrongWords(String inputData) {
        List<String> result = new ArrayList<>();
        List<String> wordList = Arrays.asList(inputData.split("[\\s.,()\\[\\]{}\\|\"':^“–‘';?!\\\\/_-]+"));

        for (int i = 0; i < wordList.size(); i++) {
            String currentWord = wordList.get(i).toLowerCase();
            if (!dictionary.contains(currentWord) && !isConvertibleToNumber(currentWord)) {
                if (!result.contains(wordList.get(i))) {
                    result.add(wordList.get(i));
                } else {
                    int count = checkCount(wordList, i);
                    String reversedWord = StringUtils.reverse(wordList.get(i));

                    for (int j = 0; j < count; j++) {
                        inputData = inputData.replaceFirst(wordList.get(i), reversedWord);
                    }
                    int index = inputData.indexOf(wordList.get(i));
                    if (index == -1) break;
                    inputData = inputData.replaceAll(reversedWord, wordList.get(i));
                    int tail = index + wordList.get(i).length() + 30;
                    String subString = inputData.substring(index, tail);
                    String nextChar = String.valueOf(inputData.charAt(tail));
                    while (!nextChar.equals(" ") || result.contains(subString)) {
                        try {
                            subString += nextChar;
                            tail += 1;
                            nextChar = String.valueOf(inputData.charAt(tail));
                        } catch (Exception ex) {
                            break;
                        }
                    }

                    subString = subString.replaceAll(" ", "%20");
                    subString = subString.replaceAll(",", "%2C");
                    subString = subString.replaceFirst(wordList.get(i), wordList.get(i) + ",-");
                    subString = clearText(subString);
                    result.add(subString);
                }
            }
        }
        return result;
    }

    private int checkCount(List<String> wordList, int i) {
        int count = 0;
        for (int j = 0; j < i; j++) {
            if (wordList.get(i).equals(wordList.get(j))) {
                count += 1;
            }
        }
        return count;
    }

    private String clearText(String subString) {
        while (subString.contains(",-%20")) subString = subString.replaceAll(",-%20", ",-");

        while (subString.endsWith(",-") || subString.startsWith("%20") || subString.endsWith("%20")) {
            if (subString.endsWith(",-")) {
                subString = subString.substring(2);
            }
            if (subString.startsWith("%20")) {
                subString = subString.substring(3);
            }
            if (subString.endsWith("%20")) {
                subString = subString.substring(0, subString.length() - 3);
            }
        }
        return subString;
    }

    private String mergeText(Elements elements) {
        StringBuilder result = new StringBuilder();
        for (Element element : elements) {
            result.append(element.text()).append("^^^");
        }

        return result.toString();
    }

    private Set<String> loadDictionary() throws IOException {
        Set<String> dictionary = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new ClassPathResource("words2.txt").getInputStream()))) {
            String word;
            while ((word = reader.readLine()) != null) {
                dictionary.add(word.trim());
            }
        }
        return dictionary;
    }

    private boolean isConvertibleToNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public int countOccurrences(String largeString, String subString) {
        int count = 0;
        int index = 0;

        while ((index = largeString.indexOf(subString, index)) != -1) {
            count++;
            index += subString.length();
        }

        return count;
    }

}
