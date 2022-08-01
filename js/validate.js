
function social_enable_disable(element,content){
    if(element.checked)
        document.getElementById(content).disabled=false
    else{
        document.getElementById(content).disabled=true
        document.getElementById(content).value=""
    }
}
function set_action(action){
    if(action=="download"){
        document.getElementById("file").disabled=true
        document.getElementById("file-name").disabled=false
    }
    else{
        document.getElementById("file").disabled=false
        document.getElementById("file-name").disabled=true
    }
}
function form_validate(){
    if(!(document.getElementById("host").checked) && !(document.getElementById("download").checked)){
        alert("Please select Action")
        document.getElementById("host").focus();
        return false
    }
    if(document.getElementById("host").checked){
        if(document.getElementById("file").value==""){
            alert("Please upload an image file")
            document.getElementById("file").focus()
            return false
        }
    }
    else{
        if(document.getElementById("file-name").value=="" || (document.getElementById("file-name").value.search(/\w+\.\w+/i)==-1)){
            alert("Please give your image file name with extension")
            document.getElementById("file-name").focus()
            return false
        }
    }
    if(document.getElementById("mobile").value.search(/\b\d{10,15}\b/)==-1){
        alert("Please enter a valid Mobile Number")
        document.getElementById("mobile").focus()
        return false
    }
}