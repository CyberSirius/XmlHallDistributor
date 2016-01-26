<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

    <xsl:template match="/">
        <html>
            <body>
                <h1>Protocol</h1>
                <xsl:apply-templates select="exam"/>
                <xsl:apply-templates select="hall"/>
                <h1>Students attending:
                    <br/>
                </h1>
                <table border="1">
                    <tr>
                        <td>Name</td>
                        <td>Faculty Number</td>
                        <td>Personal ID</td>
                        <td>Degree</td>
                        <td>Course</td>
                        <td>Group</td>
                    </tr>
                    <xsl:apply-templates/>
                </table>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="exam">
        <h2>Exam:
            <xsl:value-of select="@subject"/>
            <br/>
            <xsl:value-of select="examType"/>
            <br/>
            Exam starts:
            <xsl:apply-templates select="examStart"/>
            <br/>
            Exam ends:
            <xsl:apply-templates select="examEnd"/>
            <br/>
            <xsl:apply-templates select="hall"/>
            <br/>
        </h2>
    </xsl:template>
    <xsl:template match="hall">
        Exam held in hall
        <xsl:value-of select="@hallNumber"/>
        at
        <xsl:value-of select="faculty"/>
    </xsl:template>
    <xsl:template match="students">
        <tr>
            <td>
                <xsl:value-of select="firstName"/>&#160;<xsl:value-of select="middleName"/>&#160;<xsl:value-of
                    select="lastName"/>
            </td>
            <td>
                <xsl:value-of select="@facultyNumber"/>
            </td>
            <td>
                <xsl:value-of select="personalID"/>
            </td>
            <td>
                <xsl:value-of select="degree"/>
            </td>
            <td>
                <xsl:value-of select="course"/>
            </td>
            <td>
                <xsl:value-of select="group"/>
            </td>
        </tr>
    </xsl:template>
    <xsl:template match="examStart">
        <xsl:value-of select='format-number(number(@hour),"00")'/>:<xsl:value-of
            select='format-number(number(@minute),"00")'/> on
        <xsl:value-of select='format-number(number(@day),"00")'/>.<xsl:value-of
            select='format-number(number(@month),"00")'/>.<xsl:value-of select='format-number(number(@year),"00")'/>
    </xsl:template>
    <xsl:template match="examEnd">
        <xsl:value-of select='format-number(number(@hour),"00")'/>:<xsl:value-of
            select='format-number(number(@minute),"00")'/> on
        <xsl:value-of select='format-number(number(@day),"00")'/>.<xsl:value-of
            select='format-number(number(@month),"00")'/>.<xsl:value-of select='format-number(number(@year),"00")'/>
    </xsl:template>
</xsl:stylesheet>