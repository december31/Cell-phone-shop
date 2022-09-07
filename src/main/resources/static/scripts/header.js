class MyHeader extends HTMLElement {
	connectedCallback() {
		this.innerHTML = `
		<div id="header">
		<div class="container">
		<div id="logo" class="col-2">
			<a href="#"><img src="asset/image/logo.png" alt="logo"></a>
		</div>

		<div id="search-bar" class="col-6">
			<input type="text" placeholder="bạn cần tìm gì?">
			<button type="submit" id="search-btn">
				<i class="fa-solid fa-magnifying-glass"></i>
			</button>
		</div>

		
		<div id="main-nav-bar" class="col-4">
		<div id="support">
			<a href="#">
				<i class="fa-solid fa-phone inline-block"></i>
				<div class="text-container inline-block">
					<h4>Hỗ trợ</h4>
					<h5>012345789</h5>
				</div>
			</a>
		</div>
		
		<div id="cart">
			<a href="cart.html">
				<i class="fa-solid fa-cart-shopping inline-block"></i>
				<h4>Giỏ <br/> hàng</h4>
			</a>
		</div>

		<div id="account">
			<a href="#">
				<div class="icon-container inline-block">
					<i class="fa-solid fa-user"></i>
				</div>
				<h4>Tài khoản <br> của bạn</h4>
			</a>
		</div>
		</div>
		</div>
	</div>
	`
	}
}
customElements.define('my-header', MyHeader);

// let getAccountEmail = () => {
// 	let username = document.querySelector('#account h4')
//
// 	let request = new XMLHttpRequest()
// 	request.open('get', 'user/auto-sign-in', false)
// 	request.send()
// 	request.onreadystatechange = () => {
// 		if(request.readyState === 4) {
// 			if(request.status === 200) {
// 				console.log(request.responseText);
// 				if(request.responseText !== 'failed') {
// 					let account = JSON.parse(request.responseText);
// 					let email = account.email
// 					email = email.split('@')[0]
// 					username.innerText = `${email}`
// 				}
// 			}
// 		}
// 	}
// }
//
// getAccountEmail()