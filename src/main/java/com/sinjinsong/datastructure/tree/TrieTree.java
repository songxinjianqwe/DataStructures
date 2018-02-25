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

    public void add(String word) {
        char [] chars = word.toCharArray();
        TrieNode current = root;
        for(char c : chars) {
             if (!current.containsChildValue(c)) {
                current.addChild(c, new TrieNode(current.getValue() + c));
            }
            current = current.getChild(c);
        }
        current.setIsValidWord(true);
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

    private boolean contains(String str, boolean isWord) {
        TrieNode trieNode = getNode(str);
        return (trieNode != null && trieNode.isValidWord() && isWord) ||
                (!isWord && trieNode != null);
    }

    private TrieNode getNode(String str) {
        TrieNode current = root;
        char [] chars = str.toCharArray();
        for (int i = 0; i < chars.length && current != null; i++) {
            current = current.getChild(chars[i]);
        }
        
        return current;
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

    public boolean addChild(char c, TrieNode child) {
        children.put(c, child);
        return true;
    }

    public boolean containsChildValue(char c) {
        return children.containsKey(c);
    }

    public String getValue() {
        return value;
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
