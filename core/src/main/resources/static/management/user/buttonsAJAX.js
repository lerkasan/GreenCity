function clearAllErrorsSpan(){
    $('.errorSpan').text('');
}

let checkedCh = 0;
function updateCheckBoxCount(chInt){
    let chBox = $('#checkbox' + chInt);
    let deactivateButton = $("#btnDeactivate");
    chBox.is(":checked") ? checkedCh++ : checkedCh--;
    console.log(checkedCh)
    if(checkedCh === 0) {
        deactivateButton.addClass("disabled");
    } else deactivateButton.removeClass("disabled");
}

$(document).ready(function(){
    let deactivateButton = $("#btnDeactivate");

    // Activate tooltip
    $('[data-toggle="tooltip"]').tooltip();

    // Select/Deselect checkboxes
    var checkbox = $('table tbody input[type="checkbox"]');
    $("#selectAll").click(function(){
        if(this.checked){
            checkedCh = 0;
            checkbox.each(function(){
                this.checked = true;
                checkedCh++;
            });
            deactivateButton.removeClass("disabled");
        } else{
            checkbox.each(function(){
                checkedCh--;
                this.checked = false;
            });
            deactivateButton.addClass("disabled");
        }
    });
    checkbox.click(function(){
        if(!this.checked){
            $("#selectAll").prop("checked", false);
        }
    });

    $('#btnSearchImage').click(function (){
        let url = "/management/users?query=";
        let query = $('#inputSearch').val();
        $.ajax({
            url: url + query,
            type: 'GET',
            success: function(res) {
                window.location.href= url + query;
            }
        });
    });


    // Add user button (popup)
    $('#addUserModalBtn').on('click',function(event){
        clearAllErrorsSpan();
    });

    // Submit button in addUserModal
    $('#submitAddBtn').on('click',function(event){
        event.preventDefault();
        clearAllErrorsSpan();
        var formData = $('#addUserForm').serializeArray().reduce(function(obj, item) {
            obj[item.name] = item.value;
            return obj;
        }, {});
        var payload={
            "id" : formData.id,
            "name" : formData.name,
            "email" : formData.email,
            "role" : formData.role,
            "userStatus" : formData.userStatus
        }

        // Ajax request
        $.ajax({
            url: '/management/users/register',
            type: 'post',
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                if(Array.isArray(data.errors) && data.errors.length){
                    data.errors.forEach(function(el){
                        $(document.getElementById('errorModalSave'+el.fieldName)).text(el.fieldError);
                    })
                }
                else{
                    location.reload();
                }
            },
            data: JSON.stringify(payload)
        });
    })

    // Edit user button (popup)
    $('td .edit.eBtn').on('click',function(event){
        event.preventDefault();
        $("#editUserModal").each(function(){
            $(this).find('input.eEdit').val("");
        });
        clearAllErrorsSpan();
        $('#editUserModal').modal();
        var href = $(this).attr('href');
        $.get(href, function (user,status){
            $('#id').val(user.id);
            $('#name').val(user.name);
            $('#email').val(user.email);
            $('#role').val(user.role);
            $('#userCredo').val(user.userCredo);
            $('#userStatus').val(user.userStatus);
        });
    });
    // Save editing button in editUserModal
    $('#submitEditBtn').on('click',function(event){
        event.preventDefault();
        clearAllErrorsSpan();
        var formData = $('#editUserForm').serializeArray().reduce(function(obj, item) {
            obj[item.name] = item.value;
            return obj;
        }, {});
        var returnData={
            "id" : formData.id,
            "name" : formData.name,
            "email" : formData.email,
            "role" : formData.role,
            "userCredo" : formData.userCredo,
            "userStatus" : formData.userStatus
        }
        // put request when 'Save' button in editUserModal clicked
        $.ajax({
            url: '/management/users/',
            type: 'put',
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                if(Array.isArray(data.errors) && data.errors.length){
                    data.errors.forEach(function(el){
                        $(document.getElementById('errorModalUpdate'+el.fieldName)).text(el.fieldError);
                    })
                }
                else{
                    location.reload();
                }
            },
            data: JSON.stringify(returnData)
        });
    })

    // Deactivate user button (popup)
    $('td .deactivate-user.eDeactBtn').on('click',function(event){
        event.preventDefault();
        $('#deactivateUserModal').modal();
        var href = $(this).attr('href');
        $('#deactivateOneSubmit').attr('href',href);
    });
    // Confirm deactivation button in deactivateUserModal
    $('#deactivateOneSubmit').on('click',function(event){
        event.preventDefault();
        var href = $(this).attr('href');
        $.ajax({
            url: href,
            type: 'post',
            success: function (data) {
                location.reload();
            },
        });
    });

    // Deactivate user button (popup)
    $('td .activate-user.eActBtn').on('click',function(event){
        event.preventDefault();
        $('#activateUserModal').modal();
        var href = $(this).attr('href');
        $('#activateOneSubmit').attr('href',href);
    });
    // Confirm deactivation button in activateUserModal
    $('#activateOneSubmit').on('click',function(event){
        event.preventDefault();
        var href = $(this).attr('href');
        $.ajax({
            url: href,
            type: 'post',
            success: function (data) {
                location.reload();
            },
        });
    });

    // Deactivate marked users button (popup)
    $('#deactivateAllSubmit').on('click',function(event){
        event.preventDefault();
        var checkbox = $('table tbody input[type="checkbox"]');
        var payload=[];
        checkbox.each(function (){
            if(this.checked){
                payload.push(this.value);
            }
        })
        var href = '/management/users/deactivateAll';
        // post request when 'Deactivate marked' button in deactivateAllSelectedModal clicked
        $.ajax({
            url: href,
            type: 'post',
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                location.reload();
            },
            data: JSON.stringify(payload)
        });
    });
});
