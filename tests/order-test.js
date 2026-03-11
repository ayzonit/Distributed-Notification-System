import http from 'k6/http';
import { sleep, check } from 'k6';

export const options = {
  vus: 10,
  duration: '10s'
};

export default function () {

  const userId = Math.floor(Math.random() * 10) + 1;

  const payload = JSON.stringify({
    storeId: "STORE" + Math.floor(Math.random() * 5),
    items: [
      {
        productId: "P" + Math.floor(Math.random() * 100),
        quantity: Math.floor(Math.random() * 5) + 1
      }
    ]
  });

  const params = {
    headers: {
      'Content-Type': 'application/json'
    }
  };

  const res = http.post(
    `http://localhost:8093/api/v1/${userId}/orders`,
    payload,
    params
  );

  check(res, {
    'order created': (r) => r.status === 201
  });

  console.log(`User ${userId} order status: ${res.status}`);

  sleep(1);
}