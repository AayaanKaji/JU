<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes"/>

    <xsl:template match="/questions">
        <html>
            <head>
                <title>Quiz Questions</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                    }
                    .question {
                        margin-bottom: 20px;
                    }
                </style>
            </head>
            <body>
                <h2>Quiz Questions</h2>
                <form action="/AayaanKaji/Q26_Question_Exporter/evaluate.jsp" method="POST">
                    <xsl:for-each select="question">
                        <div class="question">
                            <p><strong><xsl:value-of select="text"/></strong></p>
                            <input type="radio" name="q{position()}" value="A"/> <xsl:value-of select="optionA"/><br/>
                            <input type="radio" name="q{position()}" value="B"/> <xsl:value-of select="optionB"/><br/>
                            <input type="radio" name="q{position()}" value="C"/> <xsl:value-of select="optionC"/><br/>
                            <input type="radio" name="q{position()}" value="D"/> <xsl:value-of select="optionD"/><br/>
                        </div>
                    </xsl:for-each>
                    <input type="submit" value="Submit Answers"/>
                </form>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
