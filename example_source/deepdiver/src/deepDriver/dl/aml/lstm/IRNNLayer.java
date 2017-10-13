package deepDriver.dl.aml.lstm;

public interface IRNNLayer {
	
	public void fTT(IBPTT bptt);
	
	public void bpTT(IBPTT bptt);

	public RNNNeuroVo [] getRNNNeuroVos();
	
	public void setRNNNeuroVos(RNNNeuroVo [] rnnvos);
	
	public void updateWw(IRNNLayerVisitor visitor); 	
	
	public LayerCfg getLc();

	public void setLc(LayerCfg lc);
	
//	public ICell [] getCells();
	
}
