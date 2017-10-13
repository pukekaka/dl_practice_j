package deepDriver.dl.aml.w2v;

import java.io.File;
import java.util.Random;

import deepDriver.dl.aml.ann.IActivationFunction;
import deepDriver.dl.aml.cnn.ActivationFactory;
import deepDriver.dl.aml.distribution.Fs;
import deepDriver.dl.aml.lstm.apps.wordSegmentation.WordSegSet;
import deepDriver.dl.aml.math.MathUtil;
import deepDriver.dl.aml.stream.IWordStream;

public class NegtiveSampling {
	
	W2V w2v = new W2V();
	
	public void w2v(IWordStream s) {
		s.reset();
		System.out.println("Init w2v...");
		while (s.hasNext()) {
			s.next();
			String [] ws = s.getSampleTT();
			String [] ts = s.getTarget();
			initW2v(ws, ts);
		}	
		System.out.println("Sort w2v...");
		w2v.etlData();		
		System.out.println("Training w2v..."); 
		for (int i = 0; i < loop; i++) {
			s.reset();
			while (s.hasNext()) {
				s.next();
				String [] ws = s.getSampleTT();
				String [] ts = s.getTarget();
				runEpich(ws, ts);
			}		
			if (i % 100 == 0) {
				save2File(i+"", "w2v", w2v);
			}
		}
		
	}
	
	int loop = 800;
	
	public void initW2v(String [] ws, String [] ts) {
		initW2v(ws);
		initW2v(ts);
	}
	
	public void initW2v(String [] ws) {
		for (int i = 0; i < ws.length; i++) {
			double [] v = w2v.getByWord(ws[i]);
			if (v == null) {
				v = w2v.generateV();
				w2v.put(ws[i], v);
			}
			if (!WordSegSet.BLANK.equals(ws[i])) {
				w2v.freshCnt(ws[i]);
			}			
		}		
	}
	
	long currentTimestamp = System.currentTimeMillis();
	
	private void save2File(String middleName, String cfgName, Object obj) {
		String sf = System.getProperty("user.dir");		
		File dir = new File(sf, "data");
		dir.mkdirs();		
		File f = null;
		if (middleName == null) {
			f = new File(dir, cfgName+"_"+currentTimestamp+".m");
		} else {
			f = new File(dir, cfgName+"_"+currentTimestamp+
					"_"+middleName+".m");
		}		
		try {
			Fs.writeObj2FileWithTs(f.getAbsolutePath(), obj);
			System.out.println("Save "+cfgName+" into "+f.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	IActivationFunction af = ActivationFactory.getAf().getAcf();
	
	public void runEpich(String [] ws, String [] ts) {
		double [] cxt = new double[w2v.getProjectionLength()];
		double [] dc = new double[w2v.getProjectionLength()];
		for (int i = 0; i < ws.length; i++) {
			if (WordSegSet.BLANK.equals(ws[i])) {
				continue;
			}
			double [] v1 = w2v.getByWord(ws[i]);
			MathUtil.plus2V(v1, cxt);
		}
		
//		MathUtil.scale(cxt, 1.0/(double)ws.length);
		
		String [] negTs = new String[nsNum]; 
		for (int i = 0; i < negTs.length; i++) {
			negTs[i] = w2v.hit(random.nextDouble());
		}
		
		double [][] dnegVs = new double[negTs.length][];
		
		for (int i = 0; i < negTs.length; i++) {
			dnegVs[i] = new double[w2v.getProjectionLength()];
			double [] v = w2v.getByWord(negTs[i]);
			double z = MathUtil.multiple(cxt, v);
			double a = af.activate(z);
			double d = l * (getLu(negTs[i], ts[0]) - a);
			MathUtil.plus2V(v, d, dc);
			MathUtil.plus2V(cxt, d, dnegVs[i]);
//			MathUtil.plus2V(cxt, d, v);
		}
		double [] v = w2v.getByWord(ts[0]);
		double z = MathUtil.multiple(cxt, v);
		double a = af.activate(z);
		double d = l * (getLu(ts[0], ts[0]) - a);
		MathUtil.plus2V(v, d, dc);
		
		//update the vectors..
		MathUtil.plus2V(cxt, d, v);		
//		MathUtil.scale(dc, 1.0/(double)ws.length);		
		for (int i = 0; i < ws.length; i++) {
			if (WordSegSet.BLANK.equals(ws[i])) {
				continue;
			}
			double [] v1 = w2v.getByWord(ws[i]);
			MathUtil.plus2V(dc, v1);
		}
		for (int i = 0; i < negTs.length; i++) {
			double [] v1 = w2v.getByWord(negTs[i]);
			MathUtil.plus2V(dnegVs[i], v1);
		}
	}
	
	public double getLu(String a, String t) {
		if (a.equals(t)) {
			return 1;
		}
		return 0;
	}
	
	int nsNum = 10;
	static transient Random random = new Random(System.currentTimeMillis());
	double l = 0.01;
	 
	
}
