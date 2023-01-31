async function getAccessToken() {
    const response = await fetch("/token");

    if (!response.ok) {
        throw new Error(response.statusText);
    }

    const accessToken = await response.text();

    return accessToken;
}

async function getUserInfo() {
    const response = await fetch("https://www.bungie.net/Platform/User/GetMembershipsForCurrentUser/", {
        method: "GET",
        headers: {
            "Authorization": "Bearer " + accessToken,
            "Content-Type": "application/json"
        }
    });

    if (!response.ok) {
        throw new Error(response.statusText);
    }

    const userInfo = await response.json();

    return userInfo;
}