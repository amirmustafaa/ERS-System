

window.onload = function(){ 

    document.getElementById("login-submit").addEventListener('click', getLoginForm);
    


}



function getLoginForm(event){
    event.preventDefault();
    login();
}




async function login(){
    let username = document.getElementById('username-input').value;
    let password = document.getElementById('password-input').value;



    const response = await fetch('http://localhost:8080/ExpenseReimbursementSystem/user/login?username='+username+'&password='+password);
    //const ourJSON = await response.json();
    //console.log(ourJSON);
    //window.location.replace("http://localhost:8080/ExpenseReimbursementSystem/resources/html/EmployeeTickets.html");
    


    
}