class MyFooter extends HTMLElement {
	connectedCallback() {
		this.innerHTML = `
		<div id="footer">
		<div class="footer-container">
			<div class="brand">
				<img src="asset/image/logo.png" alt="">
			</div>
			<div class="social-media">
				<i style="color: azure;" class="fa-brands fa-facebook"></i>
				<i style="color: red;" class="fa-brands fa-youtube"></i>
				<i style="color: blanchedalmond" class="fa-solid fa-envelope"></i>
				<i style="color: aliceblue;" class="fa-brands fa-telegram"></i>
				<i style="color: blanchedalmond;" class="fa-brands fa-tiktok"></i>
			</div>
			<div class="contact">
				<h2>Nhân viên tư vấn</h2>
				<p>0123456789</p>
				<h2>Nhân viên bảo hành</h2>
				<p>0123456789</p>
			</div>
			<div class="address">
				<h2>Hà Nội</h2>
				<p>145 ngõ 1194 đường Láng, phương Láng Thượng, quận Đống Đa, Hà Nội</p>
				<h2>Thành phố Hồ Chí Minh</h2>
				<p>phường 14, Quận 3, Thành phố Hồ Chí Minh</p>
			</div>
		</div>
	</div>
	`
	}
}
customElements.define('my-footer', MyFooter);