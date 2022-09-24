class Register extends HTMLElement {
    connectedCallback() {
        this.innerHTML = `
		<div id="register">
		<div class="register-container">
			<div class="exit">
				<i class="fa-solid fa-xmark"></i>
			</div>
			<div class="register-slider-container">
				<div class="sign-in">
					<h1>Đăng nhập</h1>
					<div class="input-container">
						<form action="sign-in" method="post">
							<label for="username-sign-in">Username</label>
							<input type="text" name="email" id="username-sign-in" required placeholder="your username">
							<label for="password-sign-in">Password</label>
							<input type="password" name="password" id="password-sign-in" required placeholder="your password">
							<input type="button" value="Sign in" id="sign-in">
						</form>
					</div>
					<p>you do not have an account? <span>sign up now</span></p>
				</div>
				<div class="sign-up">
					<h1>Đăng ký</h1>
					<div class="input-container">
						<form action="sign-up" method="post">
							<label for="username-sign-up">Username</label>
							<input type="text" name="username" id="username-sign-up" required placeholder="your username">
							<label for="email-sign-up">Email</label>
							<input type="email" name="email" id="email-sign-up" required placeholder="your email">
							<label for="password">Password</label>
							<input type="password" name="password" id="password-sign-up" required placeholder="your password">
							<label for="password-confirm">Confirm password</label>
							<input type="password" name="password" id="password-confirm" required placeholder="your password">
							<input type="button" value="sign-up" id="sign-up">
						</form>
					</div>
					<p>you have already had an account? <span>sign in now</span></p>
				</div>
			</div>
		</div>
	</div>
	`
    }
}

customElements.define('register-form', Register)


let register = document.getElementById('register')
let regButton = document.getElementById('account')
let registerContainer = document.getElementsByClassName('register-container')[0]
let exit = document.querySelector('.register-container .exit')
let changeSignInUp = document.querySelectorAll('#register p span')
let registerSliderContainer = document.querySelector('#register .register-slider-container')
let signInContainer = document.querySelector('#register .sign-in')


register.style.pointerEvents = 'none'
register.style.opacity = '0'
registerContainer.style.width = '0'
registerContainer.style.height = '0'

regButton.onclick = () => {
    registerSliderContainer.style.transition = 'none'
    register.style.animation = '0.4s reg_in'
    registerContainer.style.animation = '0.4s reg_form_in'
    registerContainer.style.width = '600px'
    registerContainer.style.height = '400px'
    register.style.opacity = '1'
    register.style.pointerEvents = 'initial'
}

let closeRegister = () => {
    register.style.animation = '0.4s reg_out'
    registerContainer.style.animation = '0.4s reg_form_out'
    registerContainer.style.width = '0px'
    registerContainer.style.height = '0px'
    register.style.opacity = '0'
    register.style.pointerEvents = 'none'

    let h = signInContainer.clientHeight
    registerSliderContainer.style.transform = `translateY(0px)`

    let oldHeight = registerContainer.clientHeight - 80;
    registerContainer.style.height = oldHeight + 'px'
}

exit.onclick = closeRegister

document.addEventListener('keydown', e => {
    if (e.key === 'Escape') {
        console.log(e.key)
        closeRegister()
    }
})

changeSignInUp[0].onclick = () => {
    let h = signInContainer.clientHeight
    registerSliderContainer.style.transition = 'ease-in-out 0.8s'
    registerSliderContainer.style.transform = `translateY(${-h - 180}px)`

    registerContainer.style.animation = 'signInToSignUp 0.8s'

    let newHeight = registerContainer.clientHeight + 180;
    registerContainer.style.height = newHeight + 'px'
}

changeSignInUp[1].onclick = () => {
    let h = signInContainer.clientHeight
    registerSliderContainer.style.transition = 'ease-in-out 0.8s'
    registerSliderContainer.style.transform = `translateY(0px)`

    registerContainer.style.animation = 'signUpToSignIn 0.8s'

    let oldHeight = registerContainer.clientHeight - 180;
    registerContainer.style.height = oldHeight + 'px'
}


let signInBtn = document.getElementById('sign-in');
let signUpBtn = document.getElementById('sign-up');

signInBtn.onclick = () => {
    let username = document.getElementById("username-sign-in").value;
    let password = document.getElementById("password-sign-in").value;
    $.ajax({
        type: "POST",
        url: "user/sign-in",
        data: {
            "username": username,
            "password": password
        },
        dataType: "text",
        success: function (response) {
            if(response !== "failed") document.cookie = `token=${response}`;
            location.reload();
        }
    });
}

signUpBtn.onclick = () => {
    let username = document.getElementById("username-sign-up").value;
    let email = document.getElementById("email-sign-up").value;
    let password = document.getElementById("password-sign-up").value;
    $.ajax({
        type: "POST",
        url: "user/sign-up",
        data: {
            "username": username,
            "email": email,
            "password": password,
        },
        dataType: "text",
        success: function (response) {
            if(response !== "failed") document.cookie = `token=${response}`;
            location.reload()
        }
    });
}
