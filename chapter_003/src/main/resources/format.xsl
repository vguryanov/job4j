<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml"/>
	<xsl:template match="/">       
		<entries>							
			<xsl:for-each select="accounts/account">    
				<entry>				
					<xsl:attribute name="href">
						<xsl:value-of select="intValue"/>						
					</xsl:attribute>     				   
				</entry>
			</xsl:for-each>  
		</entries>						
	</xsl:template>				
</xsl:stylesheet>