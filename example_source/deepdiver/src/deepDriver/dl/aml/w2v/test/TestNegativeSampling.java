package deepDriver.dl.aml.w2v.test;

import deepDriver.dl.aml.lstm.apps.wordSegmentation.WordSegSetV2;
import deepDriver.dl.aml.w2v.NegtiveSampling;
import deepDriver.dl.aml.w2v.Window4WordSegStream;

public class TestNegativeSampling {
	
	public static void main(String[] args) throws Exception {
		WordSegSetV2 wss = new WordSegSetV2();
		wss.setMaxLength(1000);
		wss.setRequireBlank(true);
		wss.setRequireEndFlagCheck(false);
		if (args.length > 0) {			
			wss.loadFlatDs(args[0]);
		} else {
			wss.loadFlatDs("D:\\6.workspace\\p.NLP\\000000_0.0");//50000-corpus.txt
		}
		
		
//		wss.setVoLoadOnly(true); 
//		wss.loadWordSegSet("D:\\6.workspace\\p.NLP\\dev.conll");
		
		Window4WordSegStream qsi = new Window4WordSegStream(wss);
		
		NegtiveSampling negtiveSampling = new NegtiveSampling();
		negtiveSampling.w2v(qsi);
		
	}

}
