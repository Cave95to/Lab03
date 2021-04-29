package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
//import java.util.HashSet;
import java.util.List;
//import java.util.Set;

public class Dictionary {
	
	private List<String> paroleDizionario;
	private String lingua;

	public Dictionary() {
	}

	public void loadDictionary(String language) {
		
		if(language.equals(this.lingua) && this.paroleDizionario!=null)
			return;
		
		this.paroleDizionario = new ArrayList<>();
		this.lingua=language;
		
		try {
			
			FileReader fr;
			
			if (language.equals("English"))
				fr = new FileReader("src/main/resources/English.txt");
			else
				fr = new FileReader("src/main/resources/Italian.txt");
			
			BufferedReader br = new BufferedReader(fr);
			
			String word;
			
			while ((word=br.readLine()) != null) {
				
				this.paroleDizionario.add(word.toLowerCase());
				
			}
			
			Collections.sort(this.paroleDizionario);
			
			System.out.println("Dizionario " + language + " loaded. Found " + this.paroleDizionario.size() + " words.");
			
			br.close();

		} catch(IOException e) {
			
			System.out.println("Errore nella lettura file");
		}
		
	}
	
	// restituiamo direttamente solo le parole sbagliate
	
	public List<RichWord> spellCheckText(List<String> inputTextList) {
		
		List<RichWord> paroleRicche = new ArrayList<>();
		
		RichWord rw;
		
		for (String s : inputTextList) {
			if (!this.paroleDizionario.contains(s)) {
				rw = new RichWord(s, false);
				paroleRicche.add(rw);
			}
		}
		
		return paroleRicche;
	}



	public void clear() {
		this.paroleDizionario.clear();
		
	}
	
	
}
