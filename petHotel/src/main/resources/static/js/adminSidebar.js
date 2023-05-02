// Sidebar 收起按鈕

var button = document.querySelector('.sidebarBtn');
      var sidebar = document.querySelector('.adminSidebar');
      var content = document.querySelector('.content');
      var svg = button.querySelector('svg');
    
      button.addEventListener('click', function() {
        sidebar.classList.toggle('d-none');
        content.classList.toggle('col-md-9');
        content.classList.toggle('col-lg-10');
        content.classList.toggle('col-md-12');
        content.classList.toggle('col-lg-12');
        var newPath = 'M8 0a8 8 0 1 1 0 16A8 8 0 0 1 8 0zM4.5 7.5a.5.5 0 0 0 0 1h5.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H4.5z';
        var currentState = button.getAttribute('data-state');
        svg.innerHTML = `<path d="${newPath}"></path>`;
        if (currentState === 'open') {
          svg.innerHTML = '<path d="M8 0a8 8 0 1 0 0 16A8 8 0 0 0 8 0zm3.5 7.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"></path>';
          button.setAttribute('data-state', 'close');
        } else {
          svg.innerHTML = `<path d="${newPath}"></path>`;
          button.setAttribute('data-state', 'open');
        }
      });