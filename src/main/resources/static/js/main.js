
function TodayDate(){
    let data= new Date();
    return data.getFullYear().toString()+'-' + (data.getMonth()+1).toString()+'-' + data.getDate().toString()
}
document.getElementById('today').innerHTML = '<input type="date" name="Data" value="'+TodayDate()+'" ><br>';