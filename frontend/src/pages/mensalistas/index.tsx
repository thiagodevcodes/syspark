import Head from "next/head";
import { ReactElement, useState, useEffect } from "react";
import styles from "../../styles/Mensalistas.module.css";
import Layout from "@/components/Layout";
import Table from "@/components/Table";
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
            <title>SysPark - Mensalistas</title>
            <meta name="description" content="Sistema SysPark" />
            <meta name="viewport" content="width=device-width, initial-scale=1" />
            <link rel="icon" href="/img/Parking.svg" />
        </Head>

        <Layout>
            
            <div className={styles.container}>
     
              <div className={styles.headerLogo}>
                <div className={styles.inputContainer}>
                  <label htmlFor="">Filtar: </label>
                  <input placeholder="Digite o nome..." type="text" />
                </div>
                <button className={styles.addButton}>+ Adicionar</button>
              </div>
            </div>
            
            <Table></Table>
        </Layout>
    </>
  );
}
