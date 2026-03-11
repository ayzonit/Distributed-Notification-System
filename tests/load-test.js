import http from 'k6/http';
import { sleep, check } from 'k6';

export const options = {
  vus: 5,        // only 1 virtual user
  duration: '10s'
};

export default function () {

  const payload = JSON.stringify({
    userId: "1",
    type: "ORDER",
    priority: "STANDARD",
    title: "Debug Test",
    message: "Debug notification"
  });

  const params = {
    headers: { 'Content-Type': 'application/json' }
  };

  const res = http.post(
    'http://127.0.0.1:8092/api/v1/notifications',
    payload,
    params
  );

  check(res, {
    'status is 201': (r) => r.status === 201
  });

  console.log("STATUS:", res.status);

  sleep(1);
}