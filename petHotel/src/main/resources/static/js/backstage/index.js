document.addEventListener("DOMContentLoaded", () => {
  const scroll_to_top_btn_el = document.querySelector("#scroll_to_top_btn");
  
  scroll_to_top_btn_el.addEventListener("click", (event) => {
	  window.scrollTo({
		  top: 0,
		  behavior:  "smooth"
	  });
  });
});