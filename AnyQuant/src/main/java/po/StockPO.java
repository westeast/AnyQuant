package po;

import data.InitialBean;

public class StockPO implements InitialBean {
	//新加了板块、地域属性
	 private String date ,name,code,board,region;
	 private double high ,low;
	 private double open,close,preClose;
	 //成交量
	 private long turnoverVol;
	 //成交额
	 private double turnoverValue;
	 //换手率
	 private double turnoverRate;
	 //市净率,市盈率
	 private double pb,pe;
	 //累积前复权因子
	 private double accAdjFactor;
	 //流通市值
	 private double cirMarketValue;
	 //总市值
	 private double totalMarketValue;
     //振幅
	 private double amplitude;
	 //变化率
	 private double changeRate;

    public StockPO(){
             super();
    }

	public StockPO(String date, String name, String code,String board,String region, double high,
			double low, double open, double close, double preClose,double accAdjFactor,
			double turnoverValue, double turnoverRate, double pe, double pb,double cirMarketValue,
			double totalMarketValue,double amplitude, double changeRate,long volume) {
		super();
		this.date = date;
		this.name = name;
		this.code = code;
		this.board=board;
		this.region=region;
		this.high = high;
		this.low = low;
		this.open = open;
		this.close = close;
		this.preClose = preClose;
		this.accAdjFactor = accAdjFactor;

		this.turnoverValue=turnoverValue;
		this.turnoverRate = turnoverRate;
		this.pe = pe;
		this.pb = pb;
		this.cirMarketValue=cirMarketValue;
		this.totalMarketValue=totalMarketValue;
		this.amplitude = amplitude;
		this.changeRate = changeRate;
		this.turnoverVol = volume;
	}

	public String getDate() {
		return date;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	public double getPe_ttm(){
		return this.pe;
	}

	public double getAdj_price(){
		return this.accAdjFactor;
	}

	public double getHigh() {
		return high;
	}

	public double getLow() {
		return low;
	}

	public double getOpen() {
		return open;
	}

	public double getClose() {
		return close;
	}



	public long getVolume() {
		return turnoverVol;
	}

	public double getTurnover() {
		return turnoverRate;
	}



	public double getPb() {
		return pb;
	}

	public double getAmplitude() {
		return amplitude;
	}

	public double getChangeRate() {
		return changeRate;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setPe_ttm(double pe_ttm){
		this.pe=pe_ttm;
	}

	public void setAdj_price(double adj_price){
		this.accAdjFactor=adj_price;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public void setClose(double close) {
		this.close = close;
	}



	public void setVolume(long volume) {
		this.turnoverVol = volume;
	}

	public void setTurnover(double turnover) {
		this.turnoverRate = turnover;
	}



	public void setPb(double pb) {
		this.pb = pb;
	}

	public void setAmplitude(double amplitude) {
		this.amplitude = amplitude;
	}

	public void setChangeRate(double changeRate) {
		this.changeRate = changeRate;
	}

	public double getPreClose() {
		return preClose;
	}

	public void setPreClose(double preClose) {
		this.preClose = preClose;
	}

	public void  computeAmplitude(){
		   double temp = Math.abs(high - low)/preClose;
		   if(temp>10){
			   temp=0;
			   return;
		   }
		   this.amplitude =    (int)(temp*10000)/10000.0;

	}

	public void  computeChangeRate(){
		   double temp = (close-open)/open;
		   if(temp>10){
			   temp=0;
			   return;
		   }
		   this.changeRate =     (int)(temp*10000)/10000.0;
	}


	public String MyToString(char a){
		return ""+date+a+name+a+code+a+board+a+region+a+high+a+low+a+open+a+close+a+preClose+a+accAdjFactor+a+turnoverValue+a+turnoverRate+a+pe+a+pb+a+cirMarketValue+a+totalMarketValue+a+amplitude+a+changeRate+a+turnoverVol;
	}


	@Override
	public void initialize() {


		 computeChangeRate();
	//	 computeAmplitude();

	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public double getTurnoverValue() {
		return turnoverValue;
	}

	public void setTurnoverValue(double turnoverValue) {
		this.turnoverValue = turnoverValue;
	}

	public double getCirMarketValue() {
		return cirMarketValue;
	}

	public void setCirMarketValue(double cirMarketValue) {
		this.cirMarketValue = cirMarketValue;
	}

	public double getAccAdjFactor() {
		return accAdjFactor;
	}

	public void setAccAdjFactor(double accAdjFactor) {
		this.accAdjFactor = accAdjFactor;
	}

	public double getTotalMarketValue() {
		return totalMarketValue;
	}

	public void setTotalMarketValue(double totalMarketValue) {
		this.totalMarketValue = totalMarketValue;
	}






}
