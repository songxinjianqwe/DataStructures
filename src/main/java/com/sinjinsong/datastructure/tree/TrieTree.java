package com.sinjinsong.datastructure.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sinjinsong
 * @date 2018/2/14
 */
public class TrieTree {
    private TrieNode root = new TrieNode("");

    public TrieTree(List<String> argInitialWords) {
        for (String word : argInitialWords) {
            add(word);
        }
    }

    public void add(String argWord) {
        char argChars[] = argWord.toCharArray();
        TrieNode currentTrieNode = root;

        for (int i = 0; i < argChars.length; i++) {
            if (!currentTrieNode.containsChildValue(argChars[i])) {
                currentTrieNode.addChild(argChars[i], new TrieNode(currentTrieNode.getValue() + argChars[i]));
            }
            currentTrieNode = currentTrieNode.getChild(argChars[i]);
        }
        currentTrieNode.setIsValidWord(true);
    }

    public boolean containsPrefix(String argPrefix) {
        return contains(argPrefix, false);
    }

    public boolean containsWord(String argWord) {
        return contains(argWord, true);
    }

    public TrieNode getWord(String argString) {
        TrieNode trieNode = getNode(argString);
        return trieNode != null && trieNode.isValidWord() ? trieNode : null;
    }

    public TrieNode getPrefix(String argString) {
        return getNode(argString);
    }

    private boolean contains(String argString, boolean argIsWord) {
        TrieNode trieNode = getNode(argString);
        return (trieNode != null && trieNode.isValidWord() && argIsWord) ||
                (!argIsWord && trieNode != null);
    }

    private TrieNode getNode(String argString) {
        TrieNode currentTrieNode = root;
        char argChars[] = argString.toCharArray();
        for (int i = 0; i < argChars.length && currentTrieNode != null; i++) {
            currentTrieNode = currentTrieNode.getChild(argChars[i]);

            if (currentTrieNode == null) {
                return null;
            }
        }

        return currentTrieNode;
    }

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));
        String words[] = {"a", "apple", "argument", "aptitude", "ball", "bat"};
        TrieTree trie = new TrieTree(Arrays.asList(words));
        try {
            while (true) {
                System.out.print("Word to lookup: ");
                String word = br.readLine().trim();
                if (word.isEmpty())
                    break;
                if (trie.containsWord(word))
                    System.out.println(word + " found");
                else if (trie.containsPrefix(word)) {
                    if (confirm(br, word + " is a prefix.  Add it as a word?"))
                        trie.add(word);
                } else {
                    if (confirm(br, "Add " + word + "?"))
                        trie.add(word);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean confirm(BufferedReader br, String question) throws IOException {

        while (true) {
            System.out.print(question + " ");
            String ans = br.readLine().trim();
            if (ans.equalsIgnoreCase("N") || ans.equalsIgnoreCase("NO"))
                return false;
            else if (ans.equalsIgnoreCase("Y") || ans.equalsIgnoreCase("YES"))
                return true;
            System.out.println("Please answer Y, YES, or N, NO");
        }
    }
}


class TrieNode {

    private final String value;
    private Map<Character, TrieNode> children = new HashMap<>();
    private boolean isValidWord;

    public TrieNode(String argValue) {
        value = argValue;
    }

    public boolean addChild(char c, TrieNode argChild) {
        children.put(c, argChild);
        return true;
    }

    public boolean containsChildValue(char c) {
        return children.containsKey(c);
    }

    public String getValue() {
        return value.toString();
    }

    public TrieNode getChild(char c) {
        return children.get(c);
    }

    public boolean isValidWord() {
        return isValidWord;
    }

    public void setIsValidWord(boolean argIsWord) {
        isValidWord = argIsWord;

    }

    public String toString() {
        return value;
    }
}
