FROM public.ecr.aws/docker/library/node:16-alpine AS builder
WORKDIR /build
RUN apk add --no-cache git openssh-client \
    && git config --global url."https://github.com/".insteadOf "git://github.com/" \
    && git config --global url."https://github.com/".insteadOf "ssh://git@github.com/" \
    && git config --global url."https://github.com/".insteadOf "git@github.com:"
COPY package*.json ./
RUN npm install --legacy-peer-deps
COPY . .
RUN npm run build:prod

FROM public.ecr.aws/docker/library/nginx:1.27-alpine
COPY --from=builder /build/dist /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
