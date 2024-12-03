// 獲取 DOM 元素 - 查詢房間
const organizerTableBody = document.querySelector('table tbody');

const fetchRooms = async () => {
    try {
        const response = await fetch('http://localhost:8989/rest/admainmanagement');
        const apiResponse = await response.json();
        console.log(apiResponse);
        displayOrganizers(apiResponse.data);
    } catch (e) {
        console.error('遠端資料存取錯誤:', e);
    }
};

// 顯示房間列表到表格
const displayOrganizers = (organizers) => {
    organizerTableBody.innerHTML = ''; // 清空表格內容，避免資料重複累加

    organizers.forEach(organizer => {
        const row = document.createElement('tr'); // 建立表格的 <tr> 元素

        // 建立各欄位
        const idCell = document.createElement('td');
        idCell.textContent = organizer.ogId;

        const accountCell = document.createElement('td');
        accountCell.textContent = organizer.ogAccount;

        const companyCell = document.createElement('td');
        companyCell.textContent = organizer.company;

        const emailCell = document.createElement('td');
        emailCell.textContent = organizer.ogEmail;

        const phoneCell = document.createElement('td');
        phoneCell.textContent = organizer.ogPhone;

        // 建立刪除按鈕欄位
        const actionCell = document.createElement('td');
        const deleteButton = document.createElement('button');
        deleteButton.textContent = '刪除';
        deleteButton.onclick = () => deleteRoom(organizer.ogId);
        actionCell.appendChild(deleteButton);

        // 將所有欄位加入到列中
        row.appendChild(idCell);
        row.appendChild(accountCell);
        row.appendChild(companyCell);
        row.appendChild(emailCell);
        row.appendChild(phoneCell);
        row.appendChild(actionCell);

        // 將列加入表格
        organizerTableBody.appendChild(row);
    });
};

fetchRooms();
