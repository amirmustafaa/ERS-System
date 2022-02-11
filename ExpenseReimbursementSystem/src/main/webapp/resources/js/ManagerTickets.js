window.onload = function(){ 

    getTickets();
    document.getElementById("logout").addEventListener("click", logout);


}

async function approve(id, choice){
    const response = await fetch('http://localhost:8080/ExpenseReimbursementSystem/user/approvedeny?id=' + id +'&choice='+choice);
    //const ourJSON = await response.json();
    await window.location.reload();
}

async function logout(){
    const response = await fetch('http://localhost:8080/ExpenseReimbursementSystem/user/logout');
}


async function getTickets(){
    const response = await fetch('http://localhost:8080/ExpenseReimbursementSystem/user/alltickets');
    const ourJSON = await response.json();

    for(let i= 0; i< ourJSON.length; i++){
        

        //step 1: creaitng our new elements
        let newTR = document.createElement("tr");
        let newTH = document.createElement("th");

        let newTD1 = document.createElement("td");
        let newTD2 = document.createElement("td");
        let newTD3 = document.createElement("td");
        let newTD4 = document.createElement("td");
        let newTD5 = document.createElement("td");
        let newTD6 = document.createElement("td");
        let newTD7 = document.createElement("td");
        let newTD8 = document.createElement("td");
        let newTD9 = document.createElement("td");
        let newTD10 = document.createElement("td");
        //step 2: populate our creations
        newTH.setAttribute("scope", "row");
        let myTextH = document.createTextNode(ourJSON[i].id);
        let myTextD1 = document.createTextNode(ourJSON[i].amount);
        let myTextD2 = document.createTextNode(ourJSON[i].submitted);
        let myTextD3 = document.createTextNode(ourJSON[i].resolved);
        let myTextD4 = document.createTextNode(ourJSON[i].description);
        let myTextD5 = document.createTextNode(ourJSON[i].receipt);
        let myTextD6 = document.createTextNode(ourJSON[i].authorId);
        let myTextD7;
        if(ourJSON[i].statusId === 1){
            myTextD7 = document.createTextNode("Pending");
        }else if(ourJSON[i].statusId === 2){
            myTextD7 = document.createTextNode("Approved");
        }else{
            myTextD7 = document.createTextNode("Denied");
        }
        let myTextD8;
        if(ourJSON[i].tyId === 1){
            myTextD8 = document.createTextNode("Lodging");
        }else if(ourJSON[i].typeId === 2){
            myTextD8 = document.createTextNode("Travel");
        }else if(ourJSON[i].typeId === 3){
            myTextD8 = document.createTextNode("Food");
        }else{
            myTextD8 = document.createTextNode("Other");
        }

        let btn1 = document.createElement("button");
        btn1.setAttribute("class", "btn btn-success");
        btn1.innerHTML = "Approve";
        btn1.onclick = function() { approve(ourJSON[i].id, "approve"); }

        let btn2 = document.createElement("button");
        btn2.setAttribute("class", "btn btn-danger");
        btn2.innerHTML = "Deny";
        btn2.onclick = function() { approve(ourJSON[i].id, "deny"); }
       
        

        

        //all appending
        newTH.appendChild(myTextH);
        newTD1.appendChild(myTextD1);
        newTD2.appendChild(myTextD2);
        newTD3.appendChild(myTextD3);
        newTD4.appendChild(myTextD4);
        newTD5.appendChild(myTextD5);
        newTD6.appendChild(myTextD6);
        newTD7.appendChild(myTextD7);
        newTD8.appendChild(myTextD8);
        newTD9.appendChild(btn1);
        newTD10.appendChild(btn2);
        
        newTR.appendChild(newTH);
        newTR.appendChild(newTD1);
        newTR.appendChild(newTD2);
        newTR.appendChild(newTD3);
        newTR.appendChild(newTD4);
        newTR.appendChild(newTD5)
        newTR.appendChild(newTD6)
        newTR.appendChild(newTD7)
        newTR.appendChild(newTD8)
        newTR.appendChild(newTD9);
        newTR.appendChild(newTD10);

        let newSelection = document.querySelector("#tableBody");
        newSelection.appendChild(newTR);
    }


}
    
