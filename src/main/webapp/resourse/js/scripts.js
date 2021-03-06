	var name;
	var change =0;
	var indexForChange=0;
		var theMessage = function(text, userName,id, del) {
		return {
			description:text,
			user : userName,
            id : ''+id+'',
            delete : !!del

		};
	};

	var ListOfMessages = [];

	var appState = {
		mainUrl : 'chat',
		history:[],
		token : 'TN11EN'
	};

function run(){
		var appContainer = document.getElementsByClassName('reader')[0];
		appContainer.addEventListener('click', delegateEvent);
		appContainer.addEventListener('change', delegateEvent);
		appContainer.addEventListener('click',delegateName);
		var messageText = document.getElementById("MessageText");
		messageText.addEventListener('keydown', function(e){
			if(e.keyCode == 13)
				onAddButtonClick(e);
		}
		)
		messageText.addEventListener('keydown', function(e){
			if(e.keyCode == 38)
				editLastMessage(e);
		}
		)
		messageText.addEventListener('keydown', function(e){
			if(e.keyCode == 40)
				deleteLastMessage(e);
		}
		)
        updateMessages();

	}


function createAllMessages(messageList) {
		for(var i = 0; i < messageList.length; i++)
        if(messageList[i].delete!="true")
			addMessage(messageList[i]);
	}
	
function deleteLastMessage(){
		var messages = document.getElementsByClassName('SeeOneMessage');
		var names = document.getElementsByClassName('nameOfUser');
		if(!messages.length)return;
		for (var i=ListOfMessages.length-1;i>0;i--){
			var element  = ListOfMessages[i];
			if(names[i].innerHTML==name+" : " && messages[i].getElementsByClassName("onlyMessage")[0].innerHTML!=" message has been deleted"){
                element.delete=false;
                deleteMessageFromServer(element, function () {  });
		        messages[i].innerHTML='<span class ="nameOfUser">' + name + ' : </span><span class="onlyMessage"> message has been deleted</span>'
		        break;
			}
		}
	}

function editLastMessage(){
    change=1;
	var messages = document.getElementsByClassName('SeeOneMessage');
    var names = document.getElementsByClassName('nameOfUser');
	if(!messages.length)return;
		for (var i=ListOfMessages.length-1;i>0;i--){
			var element  = ListOfMessages[i];
			if(names[i].innerHTML==name+" : " && messages[i].getElementsByClassName("onlyMessage")[0].innerHTML!=" message has been deleted" ){
				document.getElementById("MessageText").value = element.description;
                indexForChange=element.id;
				break;     
			}
		}
}

function delegateName(evtObj){
	if(evtObj.type === 'click' && evtObj.target.classList.contains('btn-name')){
		NameAddButtonClick(evtObj);
		}
	}

function NameAddButtonClick(){
    var MessageText = document.getElementById('NameText');
	name=MessageText.value;
	addName(MessageText.value);
	MessageText.value = '';
	document.getElementById("NameText").setAttribute('disabled',false);
	}

function addName(value){
    if(!value){ return; }
	var item = createName(value);
	var divItem =document.createElement('div');
		divItem.classList.add('form-group');
		divItem.classList.add('user');
		divItem.setAttribute('id','user');
		divItem.appendChild(item);
	var items = document.getElementsByClassName('user')[0];
		items.appendChild(divItem);
	}

function createName(text){
    var buttonItem = document.createElement('button');
		buttonItem.classList.add('btn');
		buttonItem.classList.add('btn-info');
		buttonItem.classList.add('form-group');
		buttonItem.classList.add('changeName');
		buttonItem.setAttribute('onclick','editName()');
		buttonItem.appendChild(document.createTextNode(text));
	return buttonItem;
	}

function editName() {
	document.getElementById("NameText").disabled= false;
	var user = document.getElementsByClassName("changeName");
	var element=user[user.length-1];
	document.getElementById("NameText").value= element.innerHTML;
	deleteUser();
	}

function deleteUser(){
	var users = document.getElementsByClassName('changeName');
	var element  = users[users.length-1];
		element.parentNode.removeChild(element);
	}

function delegateEvent(evtObj) {
		if(evtObj.type === 'click' && evtObj.target.classList.contains('btn-add')){
			onAddButtonClick(evtObj);
		}	
	}

function onAddButtonClick(){
	var MessageText = document.getElementById('MessageText');
	var newMessage = theMessage(MessageText.value,name,0);
		if(MessageText.value == '')
			return;
    if(change==1){
        change=0;
        var editMessage=theMessage(MessageText.value,name,indexForChange)
            MessageText.value = '';
            updateMessage(editMessage);
            changeMessages(editMessage, function(){} );
    }
    else {
        MessageText.value = '';
        storeMessages(newMessage, function () {});
    }

	}

function updateMessage(message){
    var messages = document.getElementsByClassName('SeeOneMessage');
    var names = document.getElementsByClassName('nameOfUser');
        if(!messages.length)return;
        for (var i=messages.length-1;i>0;i--){
            var element  = messages[i];
            if(names[i].innerHTML==name+" : " && messages[i].getElementsByClassName("onlyMessage")[0].innerHTML!=" message has been deleted"){
                messages[i].innerHTML='<span class ="nameOfUser">' + name + ' : </span><span class="onlyMessage">'+ message.description+'</span>'
                break;
            }
        }
    }

function addMessage(message) {
		if(!message){
			return;
		}
		var item = createMessage(message);
		var items = document.getElementsByClassName('MessagesPlace')[0];
		ListOfMessages = appState.history;
		ListOfMessages.push(message);
		items.appendChild(item);
	}

function createMessage(text){
		var divItem = document.createElement('div');
		var htmlAsText = '<div class="SeeOneMessage" ><span class ="nameOfUser"> name : </span><span class="onlyMessage">text</span></div>';
		divItem.innerHTML= htmlAsText;
		updateItem(divItem.firstChild, text);
		return divItem.firstChild;
	}

function updateItem(divItem, task){
		divItem.setAttribute('data', '"'+task.id+'"');
		divItem.lastChild.textContent = task.description;
		divItem.firstChild.innerHTML=task.user +' : ' ;
	} 

function checkConnect(evtObj) {
	 document.getElementById('connection').className="btn online";
	}


function get(url, continueWith, continueWithError) {
    ajax('GET', url, null, continueWith, continueWithError);
}

function post(url, data, continueWith, continueWithError) {
    ajax('POST', url, data, continueWith, continueWithError);
}

function put(url, data, continueWith, continueWithError) {
    ajax('PUT', url, data, continueWith, continueWithError);
}

function del(url, data, continueWith, continueWithError) {
    ajax('DELETE', url, data, continueWith, continueWithError);
}

function updateMessages(continueWith) {
        var url = appState.mainUrl + '?token=' + appState.token;

        get(url, function (responseText) {
            checkConnect();
            var response = JSON.parse(responseText);
            appState.token = response.token;
            createAllMessages(response.messages);

            continueWith && continueWith();
        },function(){
            document.getElementById('connection').className="btn offline";
        });
        setTimeout(updateMessages, 10000);
    }

    function storeMessages(sendMessage, continueWith) {
        post(appState.mainUrl, JSON.stringify(sendMessage), function(){updateMessages()});
    }
    function changeMessages(changeMessage, continueWith) {
        put(appState.mainUrl + '?id=' + changeMessage.id, JSON.stringify(changeMessage),
            function (){updateMessages()});
    }
    function deleteMessageFromServer(message,continueWith) {
        del(appState.mainUrl + '?id=' + message.id, JSON.stringify(message), function () {
            updateMessages();
        });
    }

function isError(text) {
		if(text == "")
			return false;
		try {
			var obj = JSON.parse(text);
		} catch(ex) {
			return true;
		}
		return !!obj.error;
	}

function ajax(method, url, data, continueWith, continueWithError) {
		var xhr = new XMLHttpRequest();

		continueWithError = continueWithError || defaultErrorHandler;
		xhr.open(method || 'GET', url, true);

		xhr.onload = function () {
            if (xhr.readyState !== 4)
                return;
            if (xhr.status != 304) {
                if (xhr.status != 200) {
                    continueWithError('Error on the server side, response ' + xhr.status);
                    return;
                }
                if (isError(xhr.responseText)) {
                    continueWithError('Error on the server side, response ' + xhr.responseText);
                    return;
                }
                continueWith(xhr.responseText);
            }

        };

		xhr.ontimeout = function () {
			ontinueWithError('Server timed out !');
		}

		xhr.onerror = function (e) {
			var errMsg = 'Server connection error ' + appState.mainUrl + '\n'+
                continueWithError(errMsg);
		};

		xhr.send(data);
	}

function sendMessage(message, continueWith) {
		post(appState.mainUrl, JSON.stringify(message), function(){
            continueWith();
		});
	}

function defaultErrorHandler(message) {
		console.error(message);
	}