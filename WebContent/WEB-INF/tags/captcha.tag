<%@ tag import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ tag import="net.tanesha.recaptcha.ReCaptchaFactory" %>
 
<script type="text/javascript">var RecaptchaOptions = {theme : 'clean'};</script> 
<%
    ReCaptcha reCaptcha = ReCaptchaFactory.newReCaptcha("google recaptcha public key", "google recaptcha private key", false);
    out.print(reCaptcha.createRecaptchaHtml(null, null));
%>