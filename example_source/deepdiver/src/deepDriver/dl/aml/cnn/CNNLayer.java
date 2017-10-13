package deepDriver.dl.aml.cnn;

import java.io.Serializable;


import deepDriver.dl.aml.ann.IActivationFunction;
import deepDriver.dl.aml.costFunction.ICostFunction;

public class CNNLayer implements ICNNLayer, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	IFeatureMap [] featureMaps;
	LayerConfigurator lc;
	int fmNumber;	
	ICNNLayer previousLayer;
	IActivationFunction acf;
	public CNNLayer(LayerConfigurator lc, ICNNLayer previousLayer) {
		super();
		this.previousLayer = previousLayer;
		this.lc = lc;
		fmNumber = lc.getFeatureMapNum();
		
		if (!lc.isFullConnection) {
			//it is not the same matrix as the paper
			fmNumber = lc.getFeatureMapAllocationMatrix().length;
		} 
		featureMaps = new IFeatureMap[fmNumber];
		for (int i = 0; i < featureMaps.length; i++) {
			fmIndex = i;
			featureMaps[i] = createIFeatureMap();
		}
	}
	
	public LayerConfigurator getLc() {
		return lc;
	}

	public void setLc(LayerConfigurator lc) {
		this.lc = lc;
	}

	int fmIndex = 0;
	
	public IFeatureMap createIFeatureMap() {
		int r = lc.getCkRows();
		int c = lc.getCkColumns();
		if (lc.getCks() != null) {
			r = lc.getCks()[fmIndex][0];
			c = lc.getCks()[fmIndex][1];
		}
		return new FeatureMap(this,
				lc.getAcf() == null? ActivationFactory.getAf().getReLU():lc.getAcf(), previousLayer, 
				r, c, lc.isFullConnection,
				lc.isFullConnection ? null : lc.getFeatureMapAllocationMatrix()[fmIndex], fmIndex);
	}
	
	public ICNNLayer getPreviousLayer() {
		return previousLayer;
	}

	public void setPreviousLayer(ICNNLayer previousLayer) {
		this.previousLayer = previousLayer;
	}
	
	public IFeatureMap[] getFeatureMaps() {
		return featureMaps;
	}
	public void setFeatureMaps(IFeatureMap[] featureMaps) {
		this.featureMaps = featureMaps;
	}

	@Override
	public double[] featureMaps2Vector() {
		double [][] f = featureMaps[0].getFeatures();
		double[] v = new double[featureMaps.length * 
		                        f.length * f[0].length];
		int cnt = 0;
		for (int i = 0; i < featureMaps.length; i++) {
			f = featureMaps[i].getFeatures();
			for (int j = 0; j < f.length; j++) {
				for (int j2 = 0; j2 < f[j].length; j2++) {
					v[cnt ++ ] = f[j][j2];
				}
			}
		}
		return v;
	}

	@Override
	public void accept(ICNNLayerVisitor visitor) {
		visitor.visitCNNLayer(this);		
	}
	
	ICostFunction costFunction;

	@Override
	public ICostFunction getCostFunction() {
		return costFunction;
	}	

}
