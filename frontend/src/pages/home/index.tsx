import Head from "next/head";
import { ReactElement, useState, useEffect } from "react";
import styles from "../../styles/Home.module.css";
import Layout from "@/components/Layout";
import Footer from "@/components/Footer";



export default function Home(): ReactElement {
  const [width, setWidth] = useState<number>(0)
  const [active, setActive] = useState<boolean>(false)

  useEffect(() => {
      const handleResize = () => {
        setWidth(window.innerWidth);
      };
  
      if (typeof window !== 'undefined') {
        window.addEventListener('resize', handleResize);
  
        setWidth(window.innerWidth);
  
        return () => {
          window.removeEventListener('resize', handleResize);
        };
      }
  }, [])

  return (
    <>
        <Head>
            <title>SysPark - Home</title>
            <meta name="description" content="Sistema SysPark" />
            <meta name="viewport" content="width=device-width, initial-scale=1" />
            <link rel="icon" href="/img/Parking.svg" />
        </Head>

        <Layout>
          <div className={styles.container}>
            <h1>Bem-vindo, Thiago</h1>

            <div className={styles.infoContainer}>
              <div className={styles.info}>
                <div className={styles.infoChild}>
                  <div className={styles.textContainer}>
                    <h2>Vagas Livres</h2>
                    <p>10</p>
                  </div>

                  <div className={styles.textContainer}>
                    <h2>Vagas Ocupadas</h2>
                    <p>5</p>
                  </div>
                </div>

                <div className={styles.infoChild}>
                  <div className={styles.textContainer}>
                    <h2>Total Diário</h2>
                    <p>R$10,00</p>
                  </div>

                </div>
              </div>

              <div className={styles.vagasContainer}>
                <h2>Vagas</h2>
                <div className={styles.vagas}>
                  <div className={styles.green}></div>
                  <div className={styles.red}></div>
                  <div className={styles.green}></div>
                  <div className={styles.green}></div>
                  <div className={styles.green}></div>
                  <div className={styles.green}></div>
                  <div className={styles.green}></div>
                  <div className={styles.green}></div>
                  <div className={styles.green}></div>
                  <div className={styles.green}></div>
                  <div className={styles.green}></div>
                </div>
              </div>
            </div>
          </div>
        </Layout>
    </>
  );
}
