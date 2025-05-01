<!DOCTYPE html>
<html>
<head><title>Upload XML File</title></head>
<body>
    <form action="/AayaanKaji/Q25_XML_Importer/UploadServlet" method="post" enctype="multipart/form-data">
        <input type="file" name="xmlfile" accept=".xml" required />
        <input type="submit" value="Upload and Insert Questions" />
    </form>
</body>
</html>
