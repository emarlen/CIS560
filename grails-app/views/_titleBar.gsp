<div class="titlebar">
	<div id="topHeader">
		<span id="titleText">
			Stocks and Stuff
		</span>
		<span id="loginboxes">
			<g:loginHeader/>
		</span>
	</div>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<div id="bottomHeader">
		<g:form controller="stock" method="post">
			Stock Symbol
			<g:textField class="symbolExchange" name="stockSymbol" width=5/>
			<g:actionSubmit value="Search" action="viewStock"/>
		</g:form>
	</div>
</div>