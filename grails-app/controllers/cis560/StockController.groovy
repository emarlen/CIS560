package cis560
import java.sql.ResultSet


class StockController {

    def index = {
		
		chain(controller:"stock",action:"viewStock")
	}
	
	def transaction={ }
	
	def viewStock={
		
		def stockSymbol = "FCCY"
		def stockExchange = "NASDAQ"
		if(params.stockSymbol!=null)
		{
			stockSymbol = params.stockSymbol
			String exchangeQuery = """select ename from Stock where symbol='${params.stockSymbol}'""".toString()
			SqlLogic.SetStatement(exchangeQuery)
			ResultSet exchangeResult = SqlLogic.ExecuteQuery()
			
			if(exchangeResult.next())
			{
				stockExchange=exchangeResult.getString(1)
			}
			else
			{
//TODO: RENDER THAT THE SYMBOL DOES NOT EXIST
			}
			exchangeResult.close()
			
		}
		else if(chainModel!=null&&chainModel.Symbol!=null&&chainModel.Exchange!=null)
		{
			stockSymbol = chainModel.Symbol
			stockExchange = chainModel.Exchange
		}
		String loginStatement = "select tdate,adjclose,volume from History where ename=? and symbol=? and tdate>=? ;"
		SqlLogic.SetStatement(loginStatement)
		SqlLogic.ClearParameters();
		SqlLogic.SetStringParameter(1,stockExchange)
		SqlLogic.SetStringParameter(2,stockSymbol)
		SqlLogic.SetStringParameter(3,"2011-06-1")
		
		ResultSet tableResult = SqlLogic.ExecuteQuery();
		def tablep = []
		def tablev = []
		while(tableResult.next())
		{
			tablep.add(["'"+tableResult.getString(1)+"'",tableResult.getDouble(2)])
			tablev.add(["'"+tableResult.getString(1)+"'",tableResult.getDouble(3)])
		}
		tableResult.close()

		//model to return
		def f = [["'Yesterday'", 3],["'Today'", 1],["'Tomorrow'", 1],["'Future'", 1],["'Poop'", 2]]
		[Symbol:stockSymbol, Exchange:stockExchange, Tablep:tablep]
	}
}