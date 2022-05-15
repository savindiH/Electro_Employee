$(document).ready(function (){

   //load table data on page load
   loadEmployees();

   /**
    * save or update user details when save button clicked
    */
   $('.btn-save').on('click', function (){
	
      let from_data = {};
      from_data.id = $("#txtEmployeeId").val()
      from_data.name = $("#txtName").val()
      from_data.phone = $("#txtPhone").val()
      from_data.email = $("#txtEmail").val()
      from_data.position = $("#txtPosition").val()

      //identifying request type based on button text
      const reqType = $('.btn-save').text() === 'Save' ? 'POST' : 'PUT'

      $.ajax({
         type: reqType,
         url: "webapi/employees/employee",
         contentType: 'application/json; charset=utf-8',
         dataType: 'json',
         data: JSON.stringify(from_data),
         success: function (data) {

            resetForm()

            //reload table
            loadEmployees()

            alert("Employee details saved successfully");
         },
         error: function (e) {
            alert("Something went wrong while saving data!");
         }
      });


   })

   /**
    * load user details when edit button clicked
    */
   $('#tblemployee').on('click', '.btn-edit' ,function (){

      const id = $(this).attr('id')

      $.ajax({
         type: 'GET',
         url: `webapi/employees/employee/${id}`,
         contentType: 'application/json; charset=utf-8',
         dataType: 'json',
         success: function (data) {
            if(data){
               $("#txtName").val(data.name);
               $("#txtEmail").val(data.email);
               $("#txtPosition").val(data.position);
               $("#txtPhone").val(data.phone);
               $("#txtUserId").val(data.id);

               //change button text
               $('.btn-save').text('Update')

               //reload table
               loadEmployees()

            }else {
               alert("Employee details not found!")
            }
         },
         error: function (e) {
            alert("Something went wrong while retrieving data!");
         }
      });
   })

   /**
    * delete selected user details when delete button clicked
    */
   $('#tblemployee').on('click', '.btn-delete' ,function (){

      const id = $(this).attr('id')

      $.ajax({
         type: 'DELETE',
         url: `webapi/employees/employee/${id}`,
         contentType: 'application/json; charset=utf-8',
         dataType: 'json',
         success: function (data) {
            alert("Employee details deleted successfully")

            //clear form
            resetForm()

            //reload table
            loadUsers()

         },
         error: function (e) {
            alert("Something went wrong while deleting data!");
         }
      });
   })

   //clear form
   function  resetForm(){
      $("#txtName").val(null);
      $("#txtEmail").val(null);
      $("#txtPosition").val(null);
      $("#txtContact").val(null);
      $("#txtUserId").val(null);

      //change button text
      $('.btn-save').text('Save')

   }

   /**
    * populate table data
    */
   function loadUsers(){
      $.ajax({
         type: 'GET',
         url: "webapi/employees",
         contentType: 'application/json; charset=utf-8',
         dataType: 'json',
         success: function (data) {
            $('tbody#tblEmployeesBody').empty()
            if (data !== null){

               const employees = data

               //loop through data list and populate table body
               for(i in employees){
                  $('tbody#tblUsersBody').append(
                      `<tr>
                           <td>${employees[i].name}</td>
                           <td>${employees[i].email}</td>
                           <td>${employees[i].phone}</td>
                           <td>
                             <span class="badge bg-warning text-dark btn-edit" id="${employees[i].employeeID}" style="cursor: pointer;">Edit</span>
                             <span class="badge bg-danger btn-delete" id="${employees[i].employeeID}" style="cursor: pointer;">Delete</span>
                           </td>
                       </tr>`
                  )
               }

            }else{
               `<tr>
                  <td colspan="4">Data Not found</td>                  
               </tr>`
            }
         },
         error: function (e) {
            alert("Something went wrong while loading table data!");
         }
      });
   }

});/**
 * 
 */