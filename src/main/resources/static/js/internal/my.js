function getBaseUrl() {
    let url = window.location;
    return url.protocol + "//" + url.host + "/";
}

function reloadPage() {
    window.location.reload();
}

function logout() {
    window.location.href = getBaseUrl() + "library/ui/logout";
}

function removeUserTableBody() {
    $('#userTable tbody').empty();
}

function removeBookTableBody() {
    $('#bookTable tbody').empty();
}

function removeDeliveryBookTableBody() {
    $('#deliveryBookTable tbody').empty();
}

function removePendingBookTableBody() {
    $('#pendingBookTable tbody').empty();
}

function removeStatus() {
    $('#status').empty();
}

function removeUserTable() {
    $('#userTable').hide();
}

function hideDiveImage() {
    $('#divImage').hide();
}

function removeBookTable() {
    $('#bookTable').hide();
}

function removeDeliveryBookTable() {
    $('#deliveryBookTable').hide();
}

function removePendingBookTable() {
    $('#pendingBookTable').hide();
}

function hideSearchArea() {
    $('#searchForm').hide();
}

function showUserTable() {
    $('#userTable').show();
}

function showBookTable() {
    $('#bookTable').show();
}

function showDeliveryBookTable() {
    $('#deliveryBookTable').show();
}

function showPendingBookTable() {
    $('#pendingBookTable').show();
}

function showSearchArea() {
    $('#searchForm').show();
}

let checkUser = "";

$(document).ready(
    removeUserTable(),
    removeStatus(),
    removeBookTable(),
    removePendingBookTable(),
    hideSearchArea(),
    removeDeliveryBookTable(),
    $.ajax({
        url: getBaseUrl() + 'api/book/bookCategoriList',
        type: 'GET',
        dataType: 'JSON',
        success: function (data) {
            let event_data = '';
            $.each(data, function (index, value) {
                event_data += `<option value="${value.id}">${value.name}</option>`;
            });
            $('#bookCategory').append(event_data);
            $('#bookCategoryUpdate').append(event_data);
        }
    }),
    $.ajax({
        url: getBaseUrl() + 'api/user/userRoleList',
        type: 'GET',
        dataType: 'JSON',
        success: function (data) {
            let event_data = '';
            $.each(data, function (index, value) {
                    event_data += `<option value="${value.roleId}">${value.roleName}</option>`;
                }
            );
            $('#role').append(event_data);
            $('#roleUpdate').append(event_data);

        }

    })
);


function remove() {
    removeUserTableBody();
    removeBookTableBody();
    removeDeliveryBookTableBody();
    removePendingBookTableBody();
    hideDiveImage();
}

function getBooks() {
    document.getElementById('searchField').value = '';
    document.getElementById('searchField').dataset.options = 'book';
    remove();
    //
    removeUserTable();
    removePendingBookTable();
    removeDeliveryBookTable();
    showBookTable();
    showSearchArea();
    $.ajax({
        url: getBaseUrl() + 'api/book/bookList',
        type: 'GET',
        dataType: 'JSON',
        success: function (data) {

            var event_data = '';
            $.each(data, function (index, value) {
                event_data += '<tbody class="ui-widget-content">';
                event_data += '   <tr>';
                event_data += '      <td>&nbsp;&nbsp;' + value.name + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.author + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.language + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.noCopiesActual + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.noCopiesCurrent + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.bookCategory.name + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.publicationyear + '</td>';

                event_data += '      <td>';
                event_data += '         <a onclick="editBook(' + value.id + ')" data-toggle="modal" data-target="#updateModalBook" class="btn btn-primary" >Dəyiş</a>&nbsp;&nbsp;&nbsp;&nbsp;';
                event_data += '         <a class="btn btn-danger" onclick="deleteBook(' + value.id + ')">Sil</a>&nbsp;&nbsp;&nbsp;&nbsp;';
                event_data += '      </td>';
                event_data += '   </tr>';
                event_data += '</tbody>';
            });
            $("#bookTable").append(event_data);
        }
    })

}


function getAddBookModal() {
    document.getElementById('bookName').value = '';
    document.getElementById('author').value = '';
    document.getElementById('language').value = '';
    document.getElementById('noCopiesActual').value = '';
    document.getElementById('noCopiesCurrent').value = '';
    document.getElementById('bookCategoryName').value = '';
    document.getElementById('publicationYear').value = '';

    $('#addModalBook').modal({
        backdrop: 'static'
    });
}


function addBook() {
    let bookName = $("#bookName").val();
    let author = $("#author").val();
    let language = $("#language").val();
    let noCopiesActual = $("#noCopiesActual").val();
    let noCopiesCurrent = $("#noCopiesCurrent").val();
    let bookCategoryId = $("#bookCategory").val();
    let publicationYear = $("#publicationYear").val();
    +$("#publicationYear").val();

    if (bookName === '' || author === '' || language === '' || noCopiesActual === '' || noCopiesCurrent === '' || bookCategory === '' || publicationYear === '') {
        alert("Məlumatlar tam daxil edilməmişdir");
    } else {
        let book = `bookName=${bookName}&author=${author}&language=${language}&noCopiesActual=${noCopiesActual}&noCopiesCurrent=${noCopiesCurrent}&bookCategoryId=${bookCategoryId}&publicationYear=${publicationYear}`;
        $.ajax({
                url: getBaseUrl() + 'api/book/newBook',
                method: 'POST',
                data: book,
                dataType: 'JSON',
                success: function (data) {
                    if (data === true) {
                        document.getElementById('bookName').value = '';
                        document.getElementById('author').value = '';
                        document.getElementById('language').value = '';
                        document.getElementById('noCopiesActual').value = '';
                        document.getElementById('noCopiesCurrent').value = '';
                        document.getElementById('bookCategory').value = 1;
                        document.getElementById('publicationYear').value = '';
                        $('#addModalBook').modal('hide');
                        getBooks();
                    }
                },
                error: function () {
                    alert('Xəta baş verdi!');
                }


            }
        )
    }

}


function updateBook() {

    let bookName = $("#bookNameUpdate").val();
    let author = $("#authorUpdate").val();
    let language = $("#languageUpdate").val();
    let noCopiesActual = $("#noCopiesActualUpdate").val();
    let noCopiesCurrent = $("#noCopiesCurrentUpdate").val();
    let bookCategoryId = $("#bookCategoryUpdate").val();
    let publicationYear = $("#publicationYearUpdate").val();
    if (bookName === '' || author === '' || language === '' || noCopiesActual === '' || noCopiesCurrent === '' || bookCategoryId === '' || publicationYear === '') {
        alert("Məlumatlar tam daxil edilməmişdir");
    } else {
        let book = `bookId=${$("#bookIdUpdate").val()}&name=${bookName}&author=${author}&language=${language}&noCopiesActual=${noCopiesActual}&noCopiesCurrent=${noCopiesCurrent}&bookCategoryId=${bookCategoryId}&publicationyear=${publicationYear}`;
        $.ajax({
            url: getBaseUrl() + 'api/book/updateBook',
            method: 'GET',
            data: book,
            dataType: 'JSON',
            success: function (data) {
                if (data === true) {
                    $('#updateModalBook').modal('hide');
                    getBooks();
                }
            },
            error: function () {
                alert("Xeta bash verdi");

            }


        })
    }
}

function editBook(bookId) {
    $.ajax({
        url: getBaseUrl() + 'api/book/bookById',
        data: 'bookId=' + bookId,
        type: 'GET',
        dataType: 'JSON',
        success: function (data) {
            $('#bookIdUpdate').val(data.id);
            $('#bookNameUpdate').val(data.name);
            $('#authorUpdate').val(data.author);
            $('#languageUpdate').val(data.language);
            $('#noCopiesActualUpdate').val(data.noCopiesActual);
            $('#noCopiesCurrentUpdate').val(data.noCopiesCurrent);
            $('#bookCategoryUpdate').val(data.bookCategory.id);
            $('#publicationYearUpdate').val(data.publicationyear)
        }
    });
}

function deleteBook(id) {
    if (confirm("Silmək istədiyinizə əminsiz?")) {
        $.ajax({
            url: getBaseUrl() + 'api/book/deleteBook',
            method: 'GET',
            data: "bookId=" + id,
            dataType: 'JSON',
            success: function (data) {
                if (data === true) {
                    getBooks();
                }
            },
            error: function () {
                alert('error');
            }
        });
    }
}


function getUsers() {
    document.getElementById('searchField').value = '';
    document.getElementById('searchField').dataset.options = 'user';


    remove();
    removeBookTable();
    removePendingBookTable();
    removeDeliveryBookTable();
    showUserTable();
    showSearchArea();
    $.ajax({
        url: getBaseUrl() + 'api/user/userList',
        type: 'GET',
        dataType: 'JSON',
        success: function (data) {
            var event_data = '';
            $.each(data, function (index, value) {
                event_data += '<tbody class="ui-widget-content">';
                event_data += '   <tr>';
                event_data += '      <td>&nbsp;&nbsp;' + value.username + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.fullname + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.phone + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.dob + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.registrationDate + '</td>';
                event_data += '      <td>';
                event_data += '         <a onclick="editUser(' + value.userId + ')" data-toggle="modal" data-target="#updateModalUser" class="btn btn-primary" >Dəyiş</a>&nbsp;&nbsp;&nbsp;&nbsp;';
                event_data += '         <a class="btn btn-danger" onclick="deleteUser(' + value.userId + ')" data-toggle="modal" data-target="#deleteModalUser">Sil</a>&nbsp;&nbsp;&nbsp;&nbsp;';
                event_data += '      </td>';
                event_data += '   </tr>';
                event_data += '</tbody>';

            });
            $("#userTable").append(event_data);
        }

    })

}

function getAddUpdateModal() {
    document.getElementById('bookName').value = '';
    document.getElementById('author').value = '';
    document.getElementById('language').value = '';
    document.getElementById('noCopiesActual').value = '';
    document.getElementById('noCopiesCurrent').value = '';
    document.getElementById('bookCategoryName').value = '';
    document.getElementById('publicationYear').value = '';

    $('#addModalBook').modal({
        backdrop: 'static'
    });
}

function addUser() {

    let username = $("#username").val();
    let password = $("#password").val();
    let fullName = $("#fullName").val();
    let phone = $("#phone").val();
    let userRoleId = $("#role").val();
    let dob = $("#dob").val();

    $.ajax({
        url: getBaseUrl() + 'api/user/userByName',
        type: 'GET',
        data: 'username=' + username,
        dataType: 'JSON',
        success: function (data) {
            if (data.username != null) {
                alert('Bu istifadəçi adı artıq mövcuddur!');
            } else {
                if (password === '' || fullName === '' || dob === '' || username === '' || phone === '') {
                    alert('Məlumatlar tam deyil!')
                } else {
                    let user = `username=${username}&password=${password}&fullname=${fullName}&dob=${dob}&phone=${phone}&userRoleId=${userRoleId}`;
                    $.ajax({
                        url: getBaseUrl() + 'api/user/newUser',
                        method: 'GET',
                        data: user,
                        dataType: 'JSON',
                        success: function (data) {
                            if (data === true) {
                                document.getElementById('username').value = '';
                                document.getElementById('password').value = '';
                                document.getElementById('fullName').value = '';
                                document.getElementById('role').value = '';
                                document.getElementById('dob').value = '';
                                document.getElementById('phone').value = '';
                                $('#addModalUser').modal('hide');
                                getUsers();
                            }
                        },
                        error: function () {
                            alert('error');
                        }
                    });
                }

            }
        }
    });
}


function editUser(userId) {
    $.ajax({
        url: getBaseUrl() + 'api/user/userbyid',
        data: 'userId=' + userId,
        type: 'GET',
        dataType: 'JSON',
        success: function (data) {
            $('#userIdUpdate').val(data.userId);
            $('#usernameUpdate').val(data.username);
            $('#fullNameUpdate').val(data.fullname);
            $('#phoneUpdate').val(data.phone);
            $('#dobUpdate').val(data.dob);
            $('#roleUpdate').val(data.userRole.roleId);
            checkUser = data.username;
        }


    })
}

function updateUser() {
    let username = $("#usernameUpdate").val();
    let password = $("#passwordUpdate").val();
    let fullName = $("#fullNameUpdate").val();
    let phone = $("#phoneUpdate").val();
    let role = $("#roleUpdate").val();
    let dob = $("#dobUpdate").val();

    $.ajax({
        url: getBaseUrl() + 'api/user/userByName',
        method: 'GET',
        dataType: 'JSON',
        data: 'username=' + username,
        success: function (data) {
            if (data.username != null && data.username != checkUser) {
                alert('Bu istifadəçi adı artıq mövcuddur!');
            } else if (fullName === '' || dob === '' || username === '' || password === '' || phone === '' || role === '') {
                alert('Məlumatlar tam deyil!')
            } else {
                let user = `userId=${$('#userIdUpdate').val()}&username=${username}&fullName=${fullName}&password=${password}&phone=${phone}&dob=${dob}&userRoleId=${role}`;

                $.ajax({
                    url: getBaseUrl() + 'api/user/updateUser',
                    method: 'GET',
                    data: user,
                    dataType: 'JSON',
                    success: function (data) {
                        if (data === true) {
                            $('#updateModalUser').modal('hide');
                            getUsers();
                        }
                    },
                    error: function () {
                        alert('error');
                    }
                });
            }
        }
    });
}


function deleteUser(userId) {
    if (confirm("Silmək istədiyinizə əminsiz?")) {


        $.ajax({
            url: getBaseUrl() + 'api/user/deleteUser',
            data: 'userId=' + userId,
            type: 'GET',
            dataType: 'JSON',
            success: function (data) {
                if (data === true) {
                    getUsers();
                }
            },
            error: function (data) {
                alert("Xeta bash verdi!");

            }

        })
    }

}


function getBooksUser() {

    document.getElementById('searchField').value = '';
    document.getElementById('searchField').dataset.options = 'book';
    remove();
    //

    removeUserTable();
    removePendingBookTable();

    removeDeliveryBookTable();
    showBookTable();
    showSearchArea();

    $.ajax({
        url: getBaseUrl() + 'api/book/bookList',
        type: 'GET',
        dataType: 'JSON',
        success: function (data) {

            var event_data = '';
            $.each(data, function (index, value) {
                event_data += '<tbody class="ui-widget-content">';
                event_data += '   <tr>';
                event_data += '      <td>&nbsp;&nbsp;' + value.name + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.author + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.language + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.noCopiesActual + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.noCopiesCurrent + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.bookCategory.name + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.publicationyear + '</td>';

                if (value.noCopiesCurrent > 0) {
                    event_data += '      <td>';
                    event_data += '         <a onclick="markBook(' + value.id + ')" class="btn btn-primary" >Rezerv et</a>&nbsp;&nbsp;&nbsp;&nbsp;';
                    event_data += '      </td>';
                } else {
                    event_data += '      <td>';
                    event_data += '      </td>';
                }
                event_data += '   </tr>';
                event_data += '</tbody>';
            });
            $("#bookTable").append(event_data);
        }
    })

}


function markBook(id) {
    $.ajax({
        url: getBaseUrl() + 'api/tr/addTransaction',
        method: 'GET',
        data: "bookId=" + id,
        dataType: 'JSON',
        success: function (data) {
            if (data === true) {
                getBooksUser();
            }
        },
        error: function () {
            alert('error');
        }
    });
}


function getPendingBooks() {

    document.getElementById('searchField').value = '';
    document.getElementById('searchField').dataset.options = 'pendingBook';
    remove();
    //
    removeUserTable();
    removeBookTable();
    removeDeliveryBookTable();
    showPendingBookTable();
    showSearchArea();
    $.ajax({
        url: getBaseUrl() + 'api/tr/getPendingTransactions',
        type: 'GET',
        dataType: 'JSON',
        success: function (data) {

            var event_data = '';
            $.each(data, function (index, value) {
                var statusName;
                if (value.status == 1) {
                    statusName = "Götürülmək üçün gözləmədə";
                } else if (value.status == 5) {
                    statusName = "Qaytarilmaq üçün gözləmədə";
                }

                event_data += '<tbody class="ui-widget-content">';
                event_data += '   <tr>';
                event_data += '      <td>&nbsp;&nbsp;' + value.book.name + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.book.author + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.user.fullname + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.user.phone + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.trDate + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + statusName + '</td>';


                event_data += '      <td>';
                event_data += '         <a class="btn btn-danger" onclick="deletePendingBook(' + value.trId + ')">Sil</a>&nbsp;&nbsp;&nbsp;&nbsp;';
                if (value.status == 1) {
                    event_data += '         <a class="btn btn-success" onclick="givePendingBook(' + value.trId + ')">Təsdiqlə</a>';
                } else if (value.status == 5) {
                    event_data += '         <a class="btn btn-success" onclick="takePendingBook(' + value.trId + ')">Təsdiqlə</a>';
                }
                event_data += '      </td>';
                event_data += '   </tr>';
                event_data += '</tbody>';

            });
            $("#pendingBookTable").append(event_data);
        }
    })

}


function givePendingBook(trId) {
    $.ajax({
        url: getBaseUrl() + 'api/tr/markTransactionDelivery',
        method: 'GET',
        data: "trId=" + trId,
        dataType: 'JSON',
        success: function (data) {
            if (data === true) {

                getPendingBooks();
            }
        },
        error: function () {
            alert('error');
        }
    });
}

function takePendingBook(trId) {
    $.ajax({
        url: getBaseUrl() + 'api/tr/unMarkTransactionDelivery',
        method: 'GET',
        data: "trId=" + trId,
        dataType: 'JSON',
        success: function (data) {
            if (data === true) {

                getPendingBooks();
            }
        },
        error: function () {
            alert('error');
        }
    });
}

function deletePendingBook(trId) {
    $.ajax({
        url: getBaseUrl() + 'api/tr/updateTransactionStatus',
        method: 'GET',
        data: "trId=" + trId + "&statusId=" + 0,
        dataType: 'JSON',
        success: function (data) {
            if (data === true) {
                getPendingBooks();
            }
        },
        error: function () {
            alert("Xeta bash verdi");

        }


    })

}

function getDeliveryBooks() {
    remove();
    //
    removeUserTable();
    removeBookTable();
    removePendingBookTable();
    showDeliveryBookTable();
    showSearchArea();
    document.getElementById('searchField').value = '';
    document.getElementById('searchField').dataset.options = 'deliveryBook';

    $.ajax({
        url: getBaseUrl() + 'api/tr/getDeliveryTransaction',
        type: 'GET',
        dataType: 'JSON',
        success: function (data) {
            var event_data = '';
            $.each(data, function (index, value) {
                event_data += '<tbody class="ui-widget-content">';
                event_data += '   <tr>';
                event_data += '      <td>&nbsp;&nbsp;' + value.book.name + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.book.author + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.book.language + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.user.fullname + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.user.phone + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.trDate + '</td>';
                event_data += '      <td>';
                event_data += '         <a class="btn btn-danger" onclick="deleteDeliveryBook(' + value.trId + ')">Ləğv et</a>&nbsp;&nbsp;&nbsp;&nbsp;';
                event_data += '      </td>';
                event_data += '   </tr>';
                event_data += '</tbody>';
            });
            $("#deliveryBookTable").append(event_data);
        }
    })
}

function deleteDeliveryBook(trId) {
    $.ajax({
        url: getBaseUrl() + 'api/tr/unMarkTransactionDelivery',
        method: 'GET',
        data: "trId=" + trId + "&statusId=" + 4,
        dataType: 'JSON',
        success: function (data) {
            if (data === true) {
                getDeliveryBooks();
            }
        },
        error: function () {
            alert("Xeta bash verdi");

        }


    })


}

function getDeliveryBooksUser() {

    document.getElementById('searchField').value = '';
    document.getElementById('searchField').dataset.options = 'bookDelivery';
    remove();
    //
    removeBookTable();
    removePendingBookTable();
    showDeliveryBookTable();
    showSearchArea();
    $.ajax({
        url: getBaseUrl() + 'api/tr/getDeliveryTransactionByUserId',
        type: 'GET',
        dataType: 'JSON',
        success: function (data) {
            var statusName;


            var event_data = '';
            $.each(data, function (index, value) {


                if (value.status == 3) {
                    statusName = "Istifadəçidə";
                } else if (value.status == 5) {
                    statusName = "Qaytarilmaq üçün gözləmədə";
                }


                event_data += '<tbody class="ui-widget-content">';
                event_data += '   <tr>';
                event_data += '      <td>&nbsp;&nbsp;' + value.book.name + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.book.author + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.book.language + '</td>';
                // event_data += '      <td>&nbsp;&nbsp;' + value.book.bookCategory.name + '</td>';
                // event_data += '      <td>&nbsp;&nbsp;' + value.book.publicationyear + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + statusName + '</td>';
                ;
                event_data += '                                                  <td>';
                event_data += '                                                   <td>';
                event_data += '                                                   <td>';
                event_data += '                                                   <td>';

                event_data += '         <a class="btn btn-danger" onclick="deleteDeliveryBookUser(' + value.trId + ')">Təhvil vermək</a>&nbsp;&nbsp;&nbsp;&nbsp;';
                event_data += '      </td>';
                event_data += '   </tr>';
                event_data += '</tbody>';
            });
            $("#deliveryBookTable").append(event_data);
        }
    })
}

function getPendingBooksUser() {


    document.getElementById('searchField').value = '';
    document.getElementById('searchField').dataset.options = 'bookPending';
    remove();
    //
    removeBookTable();
    removeDeliveryBookTable();
    showPendingBookTable();
    showSearchArea();
    $.ajax({
        url: getBaseUrl() + 'api/tr/getPendingTransactionByUserId',
        type: 'GET',
        dataType: 'JSON',
        success: function (data) {

            var event_data = '';
            $.each(data, function (index, value) {
                event_data += '<tbody class="ui-widget-content">';
                event_data += '   <tr>';
                event_data += '      <td>&nbsp;&nbsp;' + value.book.name + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.book.author + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.book.language + '</td>';
                // event_data += '      <td>&nbsp;&nbsp;' + value.book.bookCategory.name + '</td>';
                // event_data += '      <td>&nbsp;&nbsp;' + value.book.publicationyear + '</td>';
                event_data += '                                                  <td>';
                event_data += '                                                   <td>';
                event_data += '                                                   <td>';
                event_data += '                                                   <td>';
                event_data += '         <a class="btn btn-danger" onclick="deletePendingBookUser(' + value.trId + ')">Ləğv et</a>&nbsp;&nbsp;&nbsp;&nbsp;';
                event_data += '      </td>';
                event_data += '   </tr>';
                event_data += '</tbody>';
            });
            $("#pendingBookTable").append(event_data);
        }
    })

}


function deleteDeliveryBookUser(trİd) {

    $.ajax({
        url: getBaseUrl() + 'api/tr/updateTransactionStatus',
        data: 'trId=' + trİd + '&statusId=' + 5,
        dataType: 'JSON',
        method: 'GET',
        success: function (data) {
            if (data === true) {
                getDeliveryBooksUser();
            }

        },
        error: function () {
            alert("Xeta bash verdi");

        }
    });


}


function deletePendingBookUser(trİd) {

    $.ajax({
        url: getBaseUrl() + 'api/tr/updateTransactionStatus',
        data: 'trId=' + trİd + '&statusId=' + 6,
        dataType: 'JSON',
        method: 'GET',
        success: function (data) {
            if (data === true) {
                getPendingBooksUser();
            }

        },
        error: function () {
            alert("Xeta bash verdi");

        }
    });


}


function searchBook() {

    remove();
    removeUserTable();
    removePendingBookTable();
    removeDeliveryBookTable();
    showBookTable();

    $.ajax({
        url: getBaseUrl() + 'api/book/searchBook',
        type: 'GET',
        data: 'keyword=' + $('#searchField').val(),
        dataType: 'JSON',
        success: function (data) {

            var event_data = '';
            $.each(data, function (index, value) {
                event_data += '<tbody class="ui-widget-content">';
                event_data += '   <tr>';
                event_data += '      <td>&nbsp;&nbsp;' + value.name + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.author + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.language + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.noCopiesActual + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.noCopiesCurrent + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.bookCategory.name + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.publicationyear + '</td>';

                event_data += '      <td>';
                event_data += '         <a onclick="editBook(' + value.id + ')" data-toggle="modal" data-target="#updateModalBook" class="btn btn-primary" >Dəyiş</a>&nbsp;&nbsp;&nbsp;&nbsp;';
                event_data += '         <a class="btn btn-danger" onclick="deleteBook(' + value.id + ')">Sil</a>&nbsp;&nbsp;&nbsp;&nbsp;';
                event_data += '      </td>';
                event_data += '   </tr>';
                event_data += '</tbody>';
            });
            $("#bookTable").append(event_data);
        }
    })
}


function searchUser() {
    remove();
    removePendingBookTable();
    removeDeliveryBookTable();
    removeBookTable();
    showUserTable();


    $.ajax({
        url: getBaseUrl() + 'api/user/searchUser',
        type: 'GET',
        data: 'keyword=' + $('#searchField').val(),
        dataType: 'JSON',
        success: function (data) {

            var event_data = '';
            $.each(data, function (index, value) {
                event_data += '<tbody class="ui-widget-content">';
                event_data += '   <tr>';
                event_data += '      <td>&nbsp;&nbsp;' + value.username + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.fullname + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.phone + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.dob + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.registrationDate + '</td>';
                event_data += '      <td>';
                event_data += '         <a onclick="editUser(' + value.userId + ')" data-toggle="modal" data-target="#updateModalUser" class="btn btn-primary" >Dəyiş</a>&nbsp;&nbsp;&nbsp;&nbsp;';
                event_data += '         <a class="btn btn-danger" onclick="deleteUser(' + value.userId + ')" data-toggle="modal" data-target="#deleteModalUser">Sil</a>&nbsp;&nbsp;&nbsp;&nbsp;';
                event_data += '      </td>';
                event_data += '   </tr>';
                event_data += '</tbody>';

            });
            $("#userTable").append(event_data);
        }

    })

}


function searchDeliveryTransaction() {

    remove();
    //
    removeUserTable();
    removeBookTable();
    removePendingBookTable();
    showSearchArea();
    showDeliveryBookTable();
    $.ajax({
        url: getBaseUrl() + 'api/tr/getDeliveryTransactionSearch',
        data: 'keyword=' + $('#searchField').val(),
        type: 'GET',
        dataType: 'JSON',
        success: function (data) {
            var event_data = '';
            $.each(data, function (index, value) {
                event_data += '<tbody class="ui-widget-content">';
                event_data += '   <tr>';
                event_data += '      <td>&nbsp;&nbsp;' + value.book.name + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.book.author + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.book.language + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.user.fullname + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.user.phone + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.trDate + '</td>';
                event_data += '      <td>';
                event_data += '         <a class="btn btn-danger" onclick="deleteDeliveryBook(' + value.trId + ')">Ləğv et</a>&nbsp;&nbsp;&nbsp;&nbsp;';
                event_data += '      </td>';
                event_data += '   </tr>';
                event_data += '</tbody>';
            });
            $("#deliveryBookTable").append(event_data);
        }
    })


}

function searchPendingTransactions() {
    remove();
    removeUserTable();
    removeBookTable();
    removeDeliveryBookTable();
    showPendingBookTable();
    showSearchArea();
    $.ajax({
        url: getBaseUrl() + 'api/tr/getPendingTransactionSearch',
        data: 'keyword=' + $('#searchField').val(),
        type: 'GET',
        dataType: 'JSON',
        success: function (data) {

            var event_data = '';
            $.each(data, function (index, value) {
                var statusName;
                if (value.status == 1) {
                    statusName = "Götürülmək üçün gözləmədə";
                } else if (value.status == 5) {
                    statusName = "Qaytarilmaq üçün gözləmədə";
                }

                event_data += '<tbody class="ui-widget-content">';
                event_data += '   <tr>';
                event_data += '      <td>&nbsp;&nbsp;' + value.book.name + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.book.author + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.user.fullname + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.user.phone + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.trDate + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + statusName + '</td>';


                event_data += '      <td>';
                event_data += '         <a class="btn btn-danger" onclick="deletePendingBook(' + value.trId + ')">Sil</a>&nbsp;&nbsp;&nbsp;&nbsp;';
                if (value.status == 1) {
                    event_data += '         <a class="btn btn-success" onclick="givePendingBook(' + value.trId + ')">Təsdiqlə</a>';
                } else if (value.status == 5) {
                    event_data += '         <a class="btn btn-success" onclick="takePendingBook(' + value.trId + ')">Təsdiqlə</a>';
                }
                event_data += '      </td>';
                event_data += '   </tr>';
                event_data += '</tbody>';

            });
            $("#pendingBookTable").append(event_data);
        }
    })

}


function searchControl() {

    if (document.getElementById('searchField').dataset.options === 'book') {

        searchBook();
    } else if (document.getElementById('searchField').dataset.options === 'user') {

        searchUser();

    } else if (document.getElementById('searchField').dataset.options === 'deliveryBook') {
        searchDeliveryTransaction();
    } else if (document.getElementById('searchField').dataset.options === 'pendingBook') {
        searchPendingTransactions();
    }

}


function searchControlUser(){

    if (document.getElementById('searchField').dataset.options === 'book') {

        searchBookUSer();
    }else if (document.getElementById('searchField').dataset.options === 'bookDelivery'){

        getDeliveryBooksUserSearch();
    }else if (document.getElementById('searchField').dataset.options === 'bookPending'){
        getPendingBooksUserSearch();
    }







}


function searchBookUSer() {

    remove();

    removeUserTable();
    removePendingBookTable();
    removeDeliveryBookTable();
    showBookTable();
    showSearchArea();

    $.ajax({
        url: getBaseUrl() + 'api/book/searchBook',
        type: 'GET',
        data: 'keyword=' + $('#searchField').val(),
        dataType: 'JSON',
        success: function (data) {

            var event_data = '';
            $.each(data, function (index, value) {
                event_data += '<tbody class="ui-widget-content">';
                event_data += '   <tr>';
                event_data += '      <td>&nbsp;&nbsp;' + value.name + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.author + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.language + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.noCopiesActual + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.noCopiesCurrent + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.bookCategory.name + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.publicationyear + '</td>';

                if (value.noCopiesCurrent > 0) {
                    event_data += '      <td>';
                    event_data += '         <a onclick="markBook(' + value.id + ')" class="btn btn-primary" >Rezerv et</a>&nbsp;&nbsp;&nbsp;&nbsp;';
                    event_data += '      </td>';
                } else {
                    event_data += '      <td>';
                    event_data += '      </td>';
                }
                event_data += '   </tr>';
                event_data += '</tbody>';
            });
            $("#bookTable").append(event_data);
        }
    })

}

function getDeliveryBooksUserSearch() {

    remove();
    removeBookTable();
    removePendingBookTable();
    showDeliveryBookTable();
    showSearchArea();
    $.ajax({
        url: getBaseUrl() + 'api/tr/getDeliveryTransactionByUserIdSearch',
        data: 'keyword=' + $('#searchField').val(),
        type: 'GET',
        dataType: 'JSON',
        success: function (data) {
            var statusName;


            var event_data = '';
            $.each(data, function (index, value) {


                if (value.status == 3) {
                    statusName = "Istifadəçidə";
                } else if (value.status == 5) {
                    statusName = "Qaytarilmaq üçün gözləmədə";
                }


                event_data += '<tbody class="ui-widget-content">';
                event_data += '   <tr>';
                event_data += '      <td>&nbsp;&nbsp;' + value.book.name + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.book.author + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.book.language + '</td>';
                // event_data += '      <td>&nbsp;&nbsp;' + value.book.bookCategory.name + '</td>';
                // event_data += '      <td>&nbsp;&nbsp;' + value.book.publicationyear + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + statusName + '</td>';
                ;
                event_data += '                                                  <td>';
                event_data += '                                                   <td>';
                event_data += '                                                   <td>';
                event_data += '                                                   <td>';

                event_data += '         <a class="btn btn-danger" onclick="deleteDeliveryBookUser(' + value.trId + ')">Təhvil vermək</a>&nbsp;&nbsp;&nbsp;&nbsp;';
                event_data += '      </td>';
                event_data += '   </tr>';
                event_data += '</tbody>';
            });
            $("#deliveryBookTable").append(event_data);
        }
    })
}


function getPendingBooksUserSearch() {
    remove();
    //
    removeBookTable();
    removeDeliveryBookTable();
    showPendingBookTable();
    showSearchArea();
    $.ajax({
        url: getBaseUrl() + 'api/tr/getPendingTransactionByUserIdSearch',
        data: 'keyword=' + $('#searchField').val(),
        type: 'GET',
        dataType: 'JSON',
        success: function (data) {

            var event_data = '';
            $.each(data, function (index, value) {
                event_data += '<tbody class="ui-widget-content">';
                event_data += '   <tr>';
                event_data += '      <td>&nbsp;&nbsp;' + value.book.name + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.book.author + '</td>';
                event_data += '      <td>&nbsp;&nbsp;' + value.book.language + '</td>';
                // event_data += '      <td>&nbsp;&nbsp;' + value.book.bookCategory.name + '</td>';
                // event_data += '      <td>&nbsp;&nbsp;' + value.book.publicationyear + '</td>';
                event_data += '                                                  <td>';
                event_data += '                                                   <td>';
                event_data += '                                                   <td>';
                event_data += '                                                   <td>';
                event_data += '         <a class="btn btn-danger" onclick="deletePendingBookUser(' + value.trId + ')">Ləğv et</a>&nbsp;&nbsp;&nbsp;&nbsp;';
                event_data += '      </td>';
                event_data += '   </tr>';
                event_data += '</tbody>';
            });
            $("#pendingBookTable").append(event_data);
        }
    })

}


