
//toggle function to toggle


let showForm = false;
function toggleNewForm(actionFor){
    showForm = !showForm;
    document.getElementById(actionFor).style.display = showForm ? "block" : "none";

}

let isUpdate = true;
function toggleUpdate(editButton, updateButton){
    isUpdate = !isUpdate;
    const updateBtn = document.getElementById(updateButton);
    const editBtn = document.getElementById(editButton);


    console.log("hello world");

    if(!isUpdate){
        updateBtn.style.display = '';
        editBtn.style.display = "none";

    }

}
