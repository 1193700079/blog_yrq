// JavaScript Document

function checkData() {
    var usn = document.form.username.value;
    if(!checkUserName(usn)) {                    	//检查用户名是否符合
        return false;
    }

    var pw = document.form.password.value;
    if(!checkPassWord(pw)) {			//检查密码长度以及强度
        return false;
    }

    var repw = document.form.repassword.value;
    if(!checkRePW(repw)) {			//检查两次输入的密码是否一致
        alert("两次密码输入不一致 请重新输入");
        console.info("a a   a a a a ");
        return false;
    }

    return true;				//如果输入无误，则提交

}

function usnrule() {
    document.getElementById("usernameErr").innerHTML = "<font color='black' size='2'>请输入4-16位的用户名</font>";
}

function checkUserName(username) {
    if(username.length <3 || username.length >16) {
        document.getElementById("usernameErr").innerHTML = "<font color='red' size='2'>用户名不符合规定，请重新输入！</font>";
        return false;
    } else {
        document.getElementById("usernameErr").innerHTML = "<font color='green' size='2'>用户名正确</font>";
        return true;
    }
}

function pwrule() {
    document.getElementById("passwordErr").innerHTML = "<font color='black' size='2'>为了保障你的账户安全，请输入4位以上的密码！</font>";
}

function checkPassWord(password) {
    if(password.length < 4) {
        document.getElementById("passwordErr").innerHTML = "<font color='red' size='2'>密码太短，请重新输入！</font>";
        return false;
    } else if(password.length > 3 && password.length < 7) {
        document.getElementById("passwordErr").innerHTML = "<font color='green' size='2'>密码强度：弱！</font>";
        return true;
    } else if(password.length > 6 && password.length < 12) {
        document.getElementById("passwordErr").innerHTML = "<font color='green' size='2'>密码强度：中</font>";
        return true;
    } else if(password.length > 11 && password.length < 17) {
        document.getElementById("passwordErr").innerHTML = "<font color='green' size='2'>密码强度：强</font>";
        return true;
    } else {
        document.getElementById("passwordErr").innerHTML = "<font color='green' size='2'>密码强度：超强</font>";
        return true;
    }
}

function repwrule() {
    document.getElementById("repasswordErr").innerHTML = "<font color='black' size='2'>请重复密码</font>";
}

function checkRePW(repassword) {
    var pw = document.form.password.value;
    if(pw != repassword) {
        document.getElementById("repasswordErr").innerHTML = "<font color='red' size='2'>密码不一致</font>";
        return false;
    } else {
        document.getElementById("repasswordErr").innerHTML = "<font color='green' size='2'>密码一致</font>";
        return true;
    }
}
