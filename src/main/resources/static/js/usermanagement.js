// 獲取 DOM 元素 - 查詢房間
const organizerList = document.getElementById('organizerList');

// 透過 fetch 經由 http://localhost:8989/rest/room 取得遠端資料
const fetchRooms = async () => {
	try {
		const response = await fetch('http://localhost:8989/rest/admainmanagement');
		const apiResponse = await response.json();
		console.log(apiResponse);
		displayRooms(apiResponse.data);
	} catch(e) {
		console.error('遠端資料存取錯誤:', e);	
	}
};

// 顯示房間列表
const displayUsers = (organizers) => {
	organizerList.innerHTML = ''; // 清空 roomList 資料，避免資料重複累加
	
	organizers.forEach(organizer => {
		const listItem = document.createElement('li'); // 建立 <li> 標籤元素
		listItem.textContent = `ID: ${organizer.ogId} 帳號: ${organizer.ogAccount} 公司: ${organizer.company} 電郵: ${organizer.ogEmail} 電話: ${organizer.ogPhone} `;

		// 在 listItem 中多加入刪除元素(按鈕)
		const deleteButton = document.createElement('button');
		deleteButton.textContent = '刪除';
		deleteButton.onclick = () => deleteRoom(organizer.ogId);
		listItem.appendChild(deleteButton);
		
		// 將 listItem 加入到 organizerList 中
		organizerList.appendChild(listItem);
	});
};

fetchRooms();