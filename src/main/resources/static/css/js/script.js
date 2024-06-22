
//toggle function to toggle


let showForm = false;
function toggleNewForm(actionFor){
    showForm = !showForm;
    document.getElementById(actionFor).style.display = showForm ? "block" : "none";

}

let isUpdate = true;
function toggleUpdate(editButton, updateButton, info, update){
    isUpdate = !isUpdate;
    const updateBtn = document.getElementById(updateButton);
    const editBtn = document.getElementById(editButton);
    const infoBtn = document.getElementsByClassName(info);
    const actualUpdate = document.getElementById(update);

    if(!isUpdate){
        updateBtn.style.display = "block";
        editBtn.style.display = "none";

        for (let i = 0; i < infoBtn.length; i++) {
            infoBtn[i].style.display = 'none';

        }



        console.log(update);
    }
}
