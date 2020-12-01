/*
 *
 * login-register modal
 * Autor: Creative Tim
 * Web-autor: creative.tim
 * Web script: http://creative-tim.com
 * 
 */
function showRegisterForm(){
    $('.loginBox').fadeOut('fast',function(){
        $('.findIdBox, .findPwdBox').fadeOut('fast');
        $('.registerBox').fadeIn('fast');

        $('.login-footer').fadeOut('fast',function(){
            $('.findIdBox-footer,.findPwdBox-footer').fadeOut('fast');
            $('.register-footer').fadeIn('fast');
        });
        $('.modal-title').html('Register with');
    }); 
    $('.error').removeClass('alert alert-danger').html('');
       
}
function showLoginForm(){
    $('#loginModal .registerBox').fadeOut('fast',function(){
        $('.findIdBox, .findPwdBox').fadeOut('fast');
        $('.loginBox').fadeIn('fast');

        $('.register-footer').fadeOut('fast',function(){
            $('.login-footer, .findIdBox-footer, .findPwdBox-footer').fadeIn('fast');    
        });
        
        $('.modal-title').html('Login with');
    });       
     $('.error').removeClass('alert alert-danger').html(''); 
}

function showFindIdForm(){
    $('.loginBox').fadeOut('fast',function(){
        $('.findPwdBox').fadeOut('fast');
        $('.findIdBox').fadeIn('fast');

        $('.login-footer').fadeOut('fast',function(){
            $('.register-footer').fadeOut('fast');    
            $('.findIdBox-footer, .findPwdBox-footer').fadeIn('fast');    
        });
        
        $('.modal-title').html('Find Id');
    });       
     $('.error').removeClass('alert alert-danger').html(''); 
}

function showFindPwdForm(){
    $('.loginBox').fadeOut('fast',function(){
        $('.findIdBox').fadeOut('fast');
        $('.findPwdBox').fadeIn('fast');

        $('.login-footer').fadeOut('fast',function(){
            $('.register-footer').fadeOut('fast');    
            $('.findIdBox-footer, .findPwdBox-footer').fadeIn('fast');    
        });
        
        $('.modal-title').html('Find Password');
    });       
     $('.error').removeClass('alert alert-danger').html(''); 
}

function openLoginModal(){
    showLoginForm();
    setTimeout(function(){
        $('#loginModal').modal('show');    
    }, 230);
    
}
function openRegisterModal(){
    showRegisterForm();
    setTimeout(function(){
        $('#loginModal').modal('show');    
    }, 230);
    
}

function shakeModal(){
    $('#loginModal .modal-dialog').addClass('shake');
             $('.error').addClass('alert alert-danger').html("Invalid id/password combination");
             $('input[type="password"]').val('');
             setTimeout( function(){ 
                $('#loginModal .modal-dialog').removeClass('shake'); 
    }, 1000 ); 
}

   