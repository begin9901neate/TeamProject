document.addEventListener("DOMContentLoaded", () => {
  const aside_el = document.querySelector("aside");
  const aside_action_btn_el = document.querySelector("#aside_action_btn");
  const tag_els = document.querySelectorAll(".tag");
//  const user_tag_el = document.querySelector("#user_tag");
//  const commodity_tag_el = document.querySelector("#commodity_tag");
//  const commodity_order_tag_el = document.querySelector("#commodity_order_tag");
//  const room_tag_el = document.querySelector("#room_tag");
//  const room_order_tag_el = document.querySelector("#room_order_tag");
//  const response_tag_el = document.querySelector("#response_tag");
  const content_el = document.querySelector("content");
  const dynamically_loaded_js_file_name_el = document.querySelector("#dynamically_loaded_js_file_name");
  const loading_light_box_el = document.querySelector("#loading_light_box");
  
  aside_action_btn_el.addEventListener("click", (event) => {
	if (!aside_el.classList.contains("fold") && !aside_el.classList.contains("unfold")) {
	  aside_el.classList.add("fold");
	} else if (aside_el.classList.contains("fold")) {
	  aside_el.classList.remove("fold");
	  aside_el.classList.add("unfold");
	} else if (aside_el.classList.contains("unfold")) {
	  aside_el.classList.remove("unfold");
	  aside_el.classList.add("fold");
	}
  });
  
  tag_els.forEach(element => {
	element.addEventListener("click", (event) => {
	  const target = element.id.replace("_tag", "");
	  
	  // show loading element
	  loading_light_box_el.classList.toggle("show");
	  
	  $.ajax({
        method: "POST",
        url: "http://localhost/petHotel/backstage/" + target,
        dataType: "html",
        success: function(res) {
          content_el.innerHTML = res;
          
          // remove dynamically loaded js file
          if (!!dynamically_loaded_js_file_name_el.value) {
		    document.head.removeChild(document.querySelector("script[src='/petHotel/js/backstage/" + dynamically_loaded_js_file_name_el.value + ".js']"));
		    dynamically_loaded_js_file_name_el.value = "";
          }
		  
          // dynamically load js file
          let new_script_el = document.createElement("script");
          new_script_el.setAttribute("src", "/petHotel/js/backstage/" + target + ".js");
          document.head.appendChild(new_script_el);
          
          // success event 
          new_script_el.addEventListener("load", () => {
            console.log(target.charAt(0).toUpperCase() + target.slice(1) + " jvascript file loaded");
            
            dynamically_loaded_js_file_name_el.value = target;
            // hide loading element
            loading_light_box_el.classList.toggle("show");
          });
          
          // error event
          new_script_el.addEventListener("error", (ev) => {
            console.log("Error on loading " + target + " javascript file", ev);
          });
        },
        error: function(err) {
          console.log(err);
        },
	  });
    });
  });
  
//  user_tag_el.addEventListener("click", (event) => {
//	  const target = "user";
//	  
//	  // show loading element
//	  loading_light_box_el.classList.toggle("show");
//	  alert("該按鈕的js置於/resources/js/backstage/aside.js");
//	  
//	  $.ajax({
//        method: "POST",
//        url: "http://localhost/petHotel/backstage/" + target,
//        dataType: "html",
//        success: function(res) {
//          content_el.innerHTML = res;
//          
//          // remove dynamically loaded js file
//          if (!!dynamically_loaded_js_file_name_el.value) {
//		    document.head.removeChild(document.querySelector("script[src='/petHotel/js/backstage/" + dynamically_loaded_js_file_name_el.value + ".js']"));
//		    dynamically_loaded_js_file_name_el.value = "";
//		  }
//		  
//          // dynamically load js file
//          let new_script_el = document.createElement("script");
//          new_script_el.setAttribute("src", "/petHotel/js/backstage/" + target + ".js");
//          document.head.appendChild(new_script_el);
//          
//          // success event 
//          new_script_el.addEventListener("load", () => {
//            console.log("File loaded");
//            
//            dynamically_loaded_js_file_name_el.value = target;
//            // hide loading element
//          	loading_light_box_el.classList.toggle("show");
//          });
//          
//          // error event
//          new_script_el.addEventListener("error", (ev) => {
//            console.log("Error on loading file", ev);
//          });
//		},
//        error: function(err) {
//          console.log(err);
//        },
//	  });
//  });
//  
//  commodity_tag_el.addEventListener("click", (event) => {
//	  alert("該按鈕的js置於/resources/js/backstage/aside.js");
//  });
//  
//  commodity_order_tag_el.addEventListener("click", (event) => {
//	  alert("該按鈕的js置於/resources/js/backstage/aside.js");
//  });
//  
//  room_tag_el.addEventListener("click", (event) => {
//	  alert("該按鈕的js置於/resources/js/backstage/aside.js");
//  });
//  
//  room_order_tag_el.addEventListener("click", (event) => {
//	  alert("該按鈕的js置於/resources/js/backstage/aside.js");
//  });
//  
//  response_tag_el.addEventListener("click", (event) => {
//	  alert("該按鈕的js置於/resources/js/backstage/aside.js");
//  });
});