
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		// Your code goes here
		return str.length() > 1 ? str.substring(1) : "";
	}

	public static int levenshtein(String word1, String word2) {
		// Your code goes here
		word2 =  word2.toLowerCase();
		word1 =  word1.toLowerCase();
		if (word1.isEmpty()){
		  return word2.length();
		}
        else if (word2.isEmpty()){
		  return word1.length();
		}
		else if(word1.charAt(0) == word2.charAt(0)){
			return levenshtein(tail(word1),tail(word2));
		}else  {
			int min = Math.min(Math.min(levenshtein(tail(word1),word2), levenshtein(word1,tail(word2))), levenshtein(tail(word1),tail(word2)));
			return (min + 1);
		}
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		// Your code here
		for (int i = 0; i < dictionary.length; i++) {
			dictionary[i] = in.readLine();
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		// Your code goes here
	int minLevDistance = 999;
	String closestWord = word;
	for (int i = 0; i < dictionary.length; i++){
		int currentLevDistance = levenshtein(word, dictionary[i]);
		if (currentLevDistance < minLevDistance) {
			minLevDistance = currentLevDistance;
			closestWord = dictionary[i];
		}
	}
	if(minLevDistance > threshold){return word;}
	return closestWord;
}
}



