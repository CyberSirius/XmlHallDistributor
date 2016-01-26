<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

    <xsl:template match="/">
        <html>
            <body>
                <h1>Student Pass</h1>
                <br/>
                First name:
                <xsl:value-of select="StudentPass/student/firstName"/>
                <br/>
                Middle name:
                <xsl:value-of select="StudentPass/student/middleName"/>
                <br/>
                Last name:
                <xsl:value-of select="StudentPass/student/lastName"/>
                <br/>
                Faculty Number:
                <xsl:value-of select="StudentPass/student"/>
                <br/>
                Personal ID:
                <xsl:value-of select="StudentPass/student/personalID"/>
                <br/>
                Degree:
                <xsl:value-of select="StudentPass/student/degree"/>
                <br/>
                Course:
                <xsl:value-of select="StudentPass/student/course"/>
                <br/>
                Group:
                <xsl:value-of select="StudentPass/student/group"/>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>