var status = document.getElementById("status");
if (status.textContent == 'DISAPPROVED') {
    status.style.color = 'red';
} else if (status.textContent == 'APPROVED') {
    status.style.color = 'green';
} else if (status.textContent == 'PENDING') {
    status.style.color = 'orange';
}
console.log(status.textContent);